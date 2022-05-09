package com.superchat.messaging.services;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;

import com.superchat.messaging.model.dto.MessageRequest;
import com.superchat.messaging.model.dto.MessageResponse;
import com.superchat.messaging.model.dto.PlaceHolders;
import com.superchat.messaging.model.entity.Contact;
import com.superchat.messaging.model.entity.MessageHistory;
import com.superchat.messaging.repository.MessageRepository;
import com.superchat.messaging.services.errors.DataNotFoundException;
import com.superchat.messaging.util.BitcoinUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageSeviceImpl implements MessageAdapter {
	private final MessageRepository messageRepository;
	private final CustomerService customerService;
	private Map<String, String> placeHolders = new HashMap<>();
	
	public MessageResponse sendMessage(MessageRequest messageRequest) {
		loadPlaceHolders(messageRequest);
		
		return saveMessage(messageRequest);
	}

	private MessageResponse saveMessage(MessageRequest messageRequest) {
		if (!customerService.findCustomer(messageRequest.getCustomerEmail(), messageRequest.getCompanyName()).isPresent()) {
			throw new DataNotFoundException("Sender not found.");
		}
		
		MessageHistory messageHistory = MessageHistory
				.builder()
				.creationAt(OffsetDateTime.now())
				.companyName(messageRequest.getCompanyName())
				.sender(messageRequest.getCustomerEmail())
				.recipient(messageRequest.getRecipient())
				.body(replacePlaceHolders(messageRequest.getBody()))
				.subject(replacePlaceHolders(messageRequest.getSubject()))
				.build();
		MessageHistory message = messageRepository.save(messageHistory);
		return createMessageResponse(message);
	}
	
	public List<MessageResponse> getAllMessages(String sender, String companyName) {
		
		return messageRepository.findAllBySenderAndCompanyName(sender, companyName)
				.stream().map(message -> createMessageResponse(message)).collect(Collectors.toList());
	}
	
	private MessageResponse createMessageResponse(MessageHistory messageHistory) {
		return MessageResponse
				.builder()
				.creationAt(messageHistory.getCreationAt())
				.id(messageHistory.getId())
				.recipient(messageHistory.getRecipient())
				.subject(messageHistory.getSubject())
				.body(messageHistory.getBody())
				.build();
	}
	
	private void loadPlaceHolders(MessageRequest messageRequest) {
		Optional<Contact> recipient =  customerService.findContact(messageRequest.getCompanyName(), messageRequest.getCustomerEmail(), messageRequest.getRecipient());
		recipient.ifPresentOrElse(contact -> {
			placeHolders.put(PlaceHolders.CONTACT_NAME.value(), contact.getName());
			placeHolders.put(PlaceHolders.CURRENT_BITCOIN_PRICE.value(), BitcoinUtils.currentBitcoinPrice());
		}, () -> {
			throw new DataNotFoundException("Recipient not found");
		});
	}
	
	private String replacePlaceHolders(final String strTemplace) {
		StringSubstitutor sub = new StringSubstitutor(placeHolders);
		return sub.replace(strTemplace);
	}
	
}
