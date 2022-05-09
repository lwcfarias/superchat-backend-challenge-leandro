package com.superchat.messaging.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superchat.messaging.model.dto.ContactRequest;
import com.superchat.messaging.model.dto.ContactResponse;
import com.superchat.messaging.model.dto.CustomerRequest;
import com.superchat.messaging.model.dto.MessageRequest;
import com.superchat.messaging.model.dto.MessageResponse;
import com.superchat.messaging.services.CustomerService;
import com.superchat.messaging.services.MessageAdapter;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MessageAdapter messageAdapter;

	@PostMapping
	public void addCustomer(@RequestBody CustomerRequest newCustomer) {
		customerService.addNewCustomer(newCustomer);
	}

	@PostMapping("{companyName}/{customerEmail}/contact")
	public void addNewContact(@PathVariable String companyName, @PathVariable String customerEmail,
			@RequestBody ContactRequest newContact) {
		customerService.addNewContact(companyName, customerEmail, newContact);
	}

	@PostMapping("sendMessage")
	public void sendMessage(@RequestBody MessageRequest newMessage) {
		messageAdapter.sendMessage(newMessage);
	}

	@GetMapping("{companyName}/{customerEmail}/contact")
	public List<ContactResponse> getContacts(@PathVariable String companyName, @PathVariable String customerEmail) {
		return customerService.getContacts(customerEmail, companyName);
	}

	@GetMapping("{companyName}/{customerEmail}/messages")
	public List<MessageResponse> getMessages(@PathVariable String companyName, @PathVariable String customerEmail) {
		return messageAdapter.getAllMessages(customerEmail, companyName);
	}

}
