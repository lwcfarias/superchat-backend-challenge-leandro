package com.superchat.messaging.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class MessageRequest {
	private String companyName;
	private String customerEmail;
	private String recipient;
	private String subject;
	private String body;
}
