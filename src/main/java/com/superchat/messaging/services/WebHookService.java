package com.superchat.messaging.services;

import org.springframework.stereotype.Service;

import com.superchat.messaging.model.dto.MessageRequest;
import com.superchat.messaging.model.dto.MessageResponse;
import com.superchat.messaging.model.dto.WebHookRequest;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class WebHookService {
	private final MessageAdapter messageAdapter;
	
	public MessageResponse sendMessage(WebHookRequest webHookRequest) {
		
		MessageRequest message = MessageRequest
				.builder()
				.companyName(webHookRequest.getCompanyName())
				.customerEmail(webHookRequest.getCustomerEmail())
				.recipient(webHookRequest.getRecipient())
				.subject(webHookRequest.getSubject())
				.body(webHookRequest.getMessage())
				.build();
		
		return messageAdapter.sendMessage(message);
	}

}
