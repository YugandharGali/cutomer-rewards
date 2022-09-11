package com.customer.rewards.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.customer.rewards.dto.RewardsDTO;
import com.customer.rewards.exception.EntityNotFoundException;
import com.customer.rewards.exception.RecordExistsException;
import com.customer.rewards.service.CustomerRewardsService;

@ExtendWith(MockitoExtension.class)
class CustomerRewardsControllerTest {

	@InjectMocks
	CustomerRewardsController customerRewardsController;

	@Mock
	CustomerRewardsService rewardsService;

	@Test
	void testGetRewardsByCustomerCardNumber() throws Exception {
		// given
		RewardsDTO rewardsDTO = new RewardsDTO();
		rewardsDTO.setCardNumber("AADHAR12");
		rewardsDTO.setCustomerId(100);
		rewardsDTO.setTotalPoints(500);
		when(rewardsService.getCustomerRewards("AADHAR12")).thenReturn(rewardsDTO);

		// when
		RewardsDTO result = customerRewardsController.getRewardsByCustomerCardNumber("AADHAR12");

		// then
		assertThat(result.getCardNumber()).isEqualTo("AADHAR12");
		assertThat(result.getCustomerId()).isEqualTo(100);
		assertThat(result.getTotalPoints()).isEqualTo(500);

	}

	@Test
	void testCreateCustomer() throws RecordExistsException {
		// given
		RewardsDTO rewardsDTO = new RewardsDTO();
		rewardsDTO.setCardNumber("CARD123");
		rewardsDTO.setCustomerName("yuga");
		rewardsDTO.setCustomerId(101);
		when(rewardsService.createCustomer("CARD123", "yuga")).thenReturn(rewardsDTO);

		// when
		RewardsDTO result = customerRewardsController.createCustomer("CARD123", "yuga");

		// then
		assertThat(result.getCardNumber()).isEqualTo("CARD123");
		assertThat(result.getCustomerId()).isEqualTo(101);
	}

	@Test
	void testPostTransaction() throws NumberFormatException, EntityNotFoundException, RecordExistsException {
		// given
		RewardsDTO rewardsDTO = new RewardsDTO();
		rewardsDTO.setCardNumber("PA123");
		rewardsDTO.setCustomerName("yuga");
		rewardsDTO.setFirstMonthPoints(90);
		when(rewardsService.postTransaction("PA123", Long.valueOf("120"))).thenReturn(rewardsDTO);

		// when
		RewardsDTO result = customerRewardsController.postTransaction("PA123", Long.valueOf("120"));

		// then
		assertThat(result.getCardNumber()).isEqualTo("PA123");
		assertThat(result.getFirstMonthPoints()).isEqualTo(90);
	}

	@Test
	void testDeleteCustomer() throws EntityNotFoundException {
		// given
		CustomerRewardsService rewardsService = mock(CustomerRewardsService.class);
		lenient().doNothing().when(rewardsService).deleteCustomer("PAN123");
		
		// when
		ResponseEntity<String> result = customerRewardsController.deleteCustomer("PA123");

		// then
		assertThat(result.getBody()).contains("deleted");
	}

}
