package com.customer.rewards.repository;

import org.springframework.data.repository.CrudRepository;

import com.customer.rewards.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public Customer findByCardNumber(String cardNumber);

	public void deleteByCardNumber(String cardNumber);
}
