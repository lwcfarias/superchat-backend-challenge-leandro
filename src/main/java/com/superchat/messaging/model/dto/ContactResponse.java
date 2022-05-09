package com.superchat.messaging.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ContactResponse {
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
}
