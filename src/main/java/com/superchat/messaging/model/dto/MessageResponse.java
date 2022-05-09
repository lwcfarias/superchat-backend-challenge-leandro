package com.superchat.messaging.model.dto;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageResponse {
	private Long id;
	private OffsetDateTime creationAt;
	private String recipient;
	private String subject;
	private String body;
}
