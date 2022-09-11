package com.customer.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.dto.RewardsDTO;
import com.customer.rewards.exception.EntityNotFoundException;
import com.customer.rewards.exception.RecordExistsException;
import com.customer.rewards.service.CustomerRewardsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/customer")
@SecurityRequirement(name = "rewardapi")
public class CustomerRewardsController {

	@Autowired
	CustomerRewardsService rewardsService;

	@GetMapping(path = "/{cardNumber}/rewards")
	public RewardsDTO getRewardsByCustomerCardNumber(@PathVariable("cardNumber") String cardNumber)
			throws EntityNotFoundException {
		return rewardsService.getCustomerRewards(cardNumber);
	}

	@PostMapping(path = "/{cardNumber}/{customerName}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RewardsDTO createCustomer(@PathVariable("cardNumber") String cardNumber,
			@PathVariable("customerName") String customerName) throws RecordExistsException {
		return rewardsService.createCustomer(cardNumber, customerName);
	}

	@PostMapping(path = "/{cardNumber}/transaction/{transactionAmount}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RewardsDTO postTransaction(@PathVariable("cardNumber") String cardNumber,
			@PathVariable("transactionAmount") Long transactionAmount) throws EntityNotFoundException {
		return rewardsService.postTransaction(cardNumber, transactionAmount);
	}

	@DeleteMapping(path = "/{cardNumber}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("cardNumber") String cardNumber)
			throws EntityNotFoundException {
		rewardsService.deleteCustomer(cardNumber);
		return new ResponseEntity<>("Customer record has been deleted.", HttpStatus.OK);
	}

}
