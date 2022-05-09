package com.superchat.messaging.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.superchat.messaging.model.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	@Query("select co from Contact co where co.customer.companyName =:companyName and co.customer.email=:customerEmail and co.email=:contactEmail")
	Optional<Contact> findContactByCompany(String companyName, String customerEmail, String contactEmail);
	
	@Query("select co from Contact co where co.customer.companyName =:companyName and co.customer.email=:customerEmail")
	List<Contact> findAllContactsByCompany(String companyName, String customerEmail);
}
