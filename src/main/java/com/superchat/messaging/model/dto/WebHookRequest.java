package com.superchat.messaging.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebHookRequest {
	private String companyName;
	private String customerEmail;
	private String recipient;
	private String subject;
	private String message;
}
