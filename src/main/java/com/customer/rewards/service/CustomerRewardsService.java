package com.customer.rewards.service;

import com.customer.rewards.dto.RewardsDTO;
import com.customer.rewards.exception.EntityNotFoundException;
import com.customer.rewards.exception.RecordExistsException;

public interface CustomerRewardsService {
	public RewardsDTO getCustomerRewards(String cardNumber) throws EntityNotFoundException;

	public RewardsDTO createCustomer(String cardNumber, String customerName) throws RecordExistsException;

	public RewardsDTO postTransaction(String cardNumber, Long transactionAmount) throws EntityNotFoundException;

	public void deleteCustomer(String cardNumber) throws EntityNotFoundException;

}
