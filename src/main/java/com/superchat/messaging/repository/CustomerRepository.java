package com.superchat.messaging.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superchat.messaging.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByEmailAndCompanyName(String email, String companyName);	
}
