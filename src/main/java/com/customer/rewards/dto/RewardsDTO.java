package com.customer.rewards.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RewardsDTO {

	private long customerId;
	private String cardNumber;
	private String customerName;
	private long transactionAmount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date timestamp;

	// Response fields
	private long firstMonthPoints;
	private long secondMonthPoints;
	private long thirdMonthPoints;
	private long totalPoints;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getFirstMonthPoints() {
		return firstMonthPoints;
	}

	public void setFirstMonthPoints(long firstMonthPoints) {
		this.firstMonthPoints = firstMonthPoints;
	}

	public long getSecondMonthPoints() {
		return secondMonthPoints;
	}

	public void setSecondMonthPoints(long secondMonthPoints) {
		this.secondMonthPoints = secondMonthPoints;
	}

	public long getThirdMonthPoints() {
		return thirdMonthPoints;
	}

	public void setThirdMonthPoints(long thirdMonthPoints) {
		this.thirdMonthPoints = thirdMonthPoints;
	}

	public long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(long totalPoints) {
		this.totalPoints = totalPoints;
	}

}
