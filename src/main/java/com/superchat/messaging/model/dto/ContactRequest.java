package com.superchat.messaging.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContactRequest {
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
}
