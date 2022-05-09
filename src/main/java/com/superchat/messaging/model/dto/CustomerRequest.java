package com.superchat.messaging.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRequest {
	private String email;
	private String companyName;
}
