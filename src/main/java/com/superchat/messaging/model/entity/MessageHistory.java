package com.superchat.messaging.model.entity;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageHistory {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String companyName;
	private String sender;
	private String recipient;
	private String subject;
	private String body;

	private OffsetDateTime creationAt;

}
