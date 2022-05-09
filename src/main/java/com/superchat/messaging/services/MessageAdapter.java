package com.superchat.messaging.services;

import java.util.List;

import com.superchat.messaging.model.dto.MessageRequest;
import com.superchat.messaging.model.dto.MessageResponse;

public interface MessageAdapter {
	MessageResponse sendMessage(MessageRequest message);
	List<MessageResponse> getAllMessages(String sender, String companyName);
}
