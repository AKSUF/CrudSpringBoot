package com.powerconsuption.com.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.powerconsuption.com.entity.Customer;
import com.powerconsuption.com.payload.CustomerDto;

public interface CustomerService {

	Customer addCustomer(@Valid CustomerDto customerDto, String token);

	Customer getCustomer(UUID cid, String token);
	List<Customer> getAllCustomers(String token);

	Customer updateCustomer(UUID cid, @Valid CustomerDto customerDto, String token);

	void deleteCustomer(UUID cid, String token);

	



}
