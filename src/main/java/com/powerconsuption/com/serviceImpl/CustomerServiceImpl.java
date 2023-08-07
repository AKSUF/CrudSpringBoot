package com.powerconsuption.com.serviceImpl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerconsuption.com.config.JwTokenHelper;
import com.powerconsuption.com.entity.Customer;
import com.powerconsuption.com.exception.ResourceNotFounException;
import com.powerconsuption.com.payload.CustomerDto;
import com.powerconsuption.com.repository.CustomerRepository;
import com.powerconsuption.com.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
@Autowired
	private ModelMapper modelMapper;
@Autowired
	private CustomerRepository customerRepository;
@Autowired
private JwTokenHelper jwTokenHelper;
	
	@Override
	public Customer addCustomer(@Valid CustomerDto customerDto, String token) {
		Customer customer=this.modelMapper.map(customerDto,Customer.class);
		customer.setId(UUID.randomUUID());
		
		return this.customerRepository.save(customer);
	}


	@Override
	public Customer getCustomer(UUID cid, String token) {
		  System.out.println("Trying to get customer with cid: " + cid);
	    return customerRepository.findById(cid)
	        .orElseThrow(() -> new ResourceNotFounException("Customer", "cid: " + cid, 0));
	}


	@Override
	public List<Customer> getAllCustomers(String token) {
		 return customerRepository.findAll();
	}


	@Override
	public Customer updateCustomer(UUID cid, @Valid CustomerDto customerDto, String token) {
		   Customer existingCustomer = customerRepository.findById(cid)
			        .orElseThrow(() -> new ResourceNotFounException("Customer", "cid: " + cid, 0));
		   existingCustomer.setFirst_name(customerDto.getFirst_name());
		   existingCustomer.setLast_name(customerDto.getLast_name());
		   existingCustomer.setAddress(customerDto.getAddress());
		   existingCustomer.setCity(customerDto.getCity());
		   existingCustomer.setEmail(customerDto.getEmail());
		   existingCustomer.setPhone(customerDto.getPhone());
		   existingCustomer.setStreet(customerDto.getStreet());
		   existingCustomer.setState(customerDto.getState());
		   
		   
		return customerRepository.save(existingCustomer);
	}


	@Override
	public void deleteCustomer(UUID cid, String token) {
		  customerRepository.deleteById(cid);	// TODO Auto-generated method stub
		
	}


	

	
	
	
}
