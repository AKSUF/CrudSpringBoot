package com.powerconsuption.com.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.powerconsuption.com.config.JwTokenHelper;
import com.powerconsuption.com.entity.Customer;
import com.powerconsuption.com.payload.CustomerDto;
import com.powerconsuption.com.service.CustomerService;

@RestController
@RequestMapping("/api/v1/auth/")
public class CustomerController {
@Autowired
	private JwTokenHelper jwTokenHelper;
@Autowired
private CustomerService customerService;
@Autowired
private ModelMapper modelMapper;

@PostMapping("/create")
public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto,@RequestHeader HttpHeaders headers){
	String token = getTokenFromRequest(headers);
	Customer customer=this.customerService.addCustomer(customerDto,token);
	 
	CustomerDto newCustomer=this.modelMapper.map(customer, CustomerDto.class);
	return new ResponseEntity<CustomerDto>(newCustomer,HttpStatus.CREATED);
	
}

@GetMapping("/customer/{cid}")
public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID cid, @RequestHeader HttpHeaders headers) {
    String token = getTokenFromRequest(headers);
    Customer customer = this.customerService.getCustomer(cid, token);
    CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
    return ResponseEntity.ok(customerDto);
}

@GetMapping("/customers")
public ResponseEntity<List<CustomerDto>> getAllCustomers( @RequestHeader HttpHeaders headers) {
	 String token = getTokenFromRequest(headers);
    List<Customer> customers = this.customerService.getAllCustomers(token);
    List<CustomerDto> customerDtos = customers.stream()
            .map(customer -> modelMapper.map(customer, CustomerDto.class))
            .collect(Collectors.toList());

    return ResponseEntity.ok(customerDtos);
}
@PutMapping("/customer/{cid}")
public ResponseEntity<CustomerDto>updateCustomer(@PathVariable UUID cid, @Valid @RequestBody CustomerDto customerDto,@RequestHeader HttpHeaders headers){
	 String token = getTokenFromRequest(headers);
	Customer updateCustomer=customerService.updateCustomer(cid,customerDto,token);
	
	CustomerDto updatedCustomerDto = modelMapper.map(updateCustomer, CustomerDto.class);
    return ResponseEntity.ok(updatedCustomerDto);
	
}

@DeleteMapping("/customer/{cid}")
public ResponseEntity<Void> deleteCustomer(@PathVariable UUID cid,@RequestHeader HttpHeaders headers){
	 String token = getTokenFromRequest(headers);
	 customerService.deleteCustomer(cid,token);
     return ResponseEntity.noContent().build();
	
}



private String getTokenFromRequest(HttpHeaders headers) {
    // Get the token from the "Authorization" header
    String authorizationHeader = headers.getFirst("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        return authorizationHeader.substring(7);
    } else {
        // Handle the case when the token is not found in the request header
        throw new RuntimeException("JWT token not found in request header.");
    }
}



}
