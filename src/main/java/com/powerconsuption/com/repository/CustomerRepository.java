package com.powerconsuption.com.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerconsuption.com.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,UUID>{

}
