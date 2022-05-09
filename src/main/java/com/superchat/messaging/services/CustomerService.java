package com.superchat.messaging.services;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.superchat.messaging.model.dto.ContactRequest;
import com.superchat.messaging.model.dto.ContactResponse;
import com.superchat.messaging.model.dto.CustomerRequest;
import com.superchat.messaging.model.entity.Contact;
import com.superchat.messaging.model.entity.Customer;
import com.superchat.messaging.repository.ContactRepository;
import com.superchat.messaging.repository.CustomerRepository;
import com.superchat.messaging.services.errors.DataAlreadyExistException;
import com.superchat.messaging.services.errors.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final ContactRepository contactRepository;
	
	public void addNewCustomer(CustomerRequest newCustomer) {
		if (findCustomer(newCustomer.getEmail(), newCustomer.getCompanyName()).isPresent()) {
			throw new DataAlreadyExistException("User is alreay in use.");
		}
	
		Customer customer = Customer
				.builder()
				.creationAt(OffsetDateTime.now())
				.email(newCustomer.getEmail())
				.companyName(newCustomer.getCompanyName())
				.build();
		
		customerRepository.save(customer);
	}

	public void addNewContact(String companyName, String customerEmail, ContactRequest newContact) {
		Customer customer = customerRepository.findByEmailAndCompanyName(customerEmail, companyName)
				.orElseThrow(() -> new DataNotFoundException("Customer not found."));
		
		if (contactRepository.findContactByCompany(companyName, customerEmail, newContact.getEmail()).isPresent()) {
			throw new DataAlreadyExistException("Contact alreay exists.");
		}
	
		Contact contact = Contact
				.builder()
				.creationAt(OffsetDateTime.now())
				.customer(customer)
				.email(newContact.getEmail())
				.name(newContact.getName())
				.phoneNumber(newContact.getPhoneNumber())
				.address(newContact.getAddress())
				.build();
		contactRepository.save(contact);
	}
	
	public List<ContactResponse> getContacts(String customerEmail, String companyName) {
		Optional<Customer> customer = customerRepository.findByEmailAndCompanyName(customerEmail, companyName);
		if (customer.isPresent()) {
			return mapResponseContacts(customer.get().getContacts());
		}
		return Collections.emptyList();
	}
	
	private  List<ContactResponse> mapResponseContacts(final List<Contact> contacts) {
		return contacts.stream()
				.map(contact -> ContactResponse
						.builder()
						.name(contact.getName())
						.email(contact.getEmail())
						.phoneNumber(contact.getPhoneNumber())
						.address(contact.getAddress())
						.build())
				.collect(Collectors.toList());
	}
	
	public Optional<Contact> findContact(String companyName, String customerEmail, String contactEmail) {
		return contactRepository.findContactByCompany(companyName, customerEmail, contactEmail);
	}
	
	public Optional<Customer> findCustomer(String customerEmail, String companyName) {
		return customerRepository.findByEmailAndCompanyName(customerEmail, companyName);
	}
}
