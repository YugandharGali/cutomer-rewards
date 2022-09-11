package com.customer.rewards.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.rewards.dto.RewardsDTO;
import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.exception.EntityNotFoundException;
import com.customer.rewards.exception.RecordExistsException;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.repository.TransactionRepository;
import com.customer.rewards.service.CustomerRewardsService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerRewardsServiceImpl implements CustomerRewardsService {

	public static final int MONTH_NO_DAYS = 30;
	public static final int FIRST_REWARD_LIMIT = 50; // spent over $50 on the transaction
	public static final int SECOND_REWARD_LIMIT = 100; // spent over $100 on the transaction

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	public RewardsDTO getCustomerRewards(String cardNumber) throws EntityNotFoundException {

		Customer customer = validateCustomerRecord(cardNumber);

		Timestamp firstMonthDate = getDateBasedOnOffSetDays(MONTH_NO_DAYS);
		Timestamp secondMonthDate = getDateBasedOnOffSetDays(2 * MONTH_NO_DAYS);
		Timestamp thirdMonthDate = getDateBasedOnOffSetDays(3 * MONTH_NO_DAYS);

		// Get transactions between dates for 3 months.
		List<Transaction> lastMonthTransactions = transactionRepository
				.findAllByCustomerAndTransactionDateBetween(customer, firstMonthDate, Timestamp.from(Instant.now()));
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerAndTransactionDateBetween(customer, secondMonthDate, firstMonthDate);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerAndTransactionDateBetween(customer, thirdMonthDate, secondMonthDate);

		// Calculate reward points for each month
		Long firstMonthPoints = getRewardsPerMonth(lastMonthTransactions);
		Long secondMonthPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long thirdMonthPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		RewardsDTO customerRewards = new RewardsDTO();
		customerRewards.setCardNumber(cardNumber);
		customerRewards.setCustomerId(customer.getCustomerId());
		customerRewards.setCustomerName(customer.getCustomerName());
		customerRewards.setFirstMonthPoints(firstMonthPoints);
		customerRewards.setSecondMonthPoints(secondMonthPoints);
		customerRewards.setThirdMonthPoints(thirdMonthPoints);
		customerRewards.setTotalPoints(firstMonthPoints + secondMonthPoints + thirdMonthPoints);

		return customerRewards;

	}

	private Customer validateCustomerRecord(String cardNumber) throws EntityNotFoundException {
		Customer customer = customerRepository.findByCardNumber(cardNumber);
		if (customer == null) {
			throw new EntityNotFoundException("Invalid customer card: " + cardNumber);
		}
		return customer;
	}

	public Timestamp getDateBasedOnOffSetDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

	private Long getRewardsPerMonth(List<Transaction> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(rec -> rec.longValue()));
	}

	private Long calculateRewards(Transaction transaction) {
		if (transaction.getTransactionAmount() > FIRST_REWARD_LIMIT
				&& transaction.getTransactionAmount() <= SECOND_REWARD_LIMIT)
			return Math.round(transaction.getTransactionAmount() - FIRST_REWARD_LIMIT);
		else if (transaction.getTransactionAmount() > SECOND_REWARD_LIMIT)
			return Math.round(transaction.getTransactionAmount() - FIRST_REWARD_LIMIT)
					+ Math.round(transaction.getTransactionAmount() - SECOND_REWARD_LIMIT);
		else
			return 0l;

	}

	@Override
	public RewardsDTO postTransaction(String cardNumber, Long transactionAmount) throws EntityNotFoundException {
		Customer customer = validateCustomerRecord(cardNumber);
		// Save transaction
		Transaction transactionSave = new Transaction();
		transactionSave.setCustomer(customer);
		transactionSave.setTransactionAmount(transactionAmount);
		transactionSave.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
		transactionRepository.save(transactionSave);

		return getCustomerRewards(customer.getCardNumber());
	}

	@Override
	public RewardsDTO createCustomer(String cardNumber, String customerName) throws RecordExistsException {
		Customer customer = customerRepository.findByCardNumber(cardNumber);
		if (customer != null) {
			throw new RecordExistsException("Customer already exist with card number:" + cardNumber);
		}
		// Save customer
		Customer customerSave = new Customer();
		customerSave.setCardNumber(cardNumber);
		customerSave.setCustomerName(customerName);
		customerRepository.save(customerSave);

		// Get newly created customer
		Customer customerNew = customerRepository.findByCardNumber(cardNumber);
		RewardsDTO rewardsDTO = new RewardsDTO();
		rewardsDTO.setCustomerId(customerNew.getCustomerId());
		rewardsDTO.setCardNumber(customerNew.getCardNumber());
		rewardsDTO.setCustomerName(customerNew.getCustomerName());
		return rewardsDTO;
	}

	@Override
	public void deleteCustomer(String cardNumber) throws EntityNotFoundException {
		this.validateCustomerRecord(cardNumber);
		customerRepository.deleteByCardNumber(cardNumber);
	}

}
