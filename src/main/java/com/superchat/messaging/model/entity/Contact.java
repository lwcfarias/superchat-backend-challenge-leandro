package com.superchat.messaging.model.entity;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "contact", uniqueConstraints={@UniqueConstraint(columnNames={"email", "customer_id"})})
public class Contact {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@ManyToOne
	private Customer customer;
	
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private OffsetDateTime creationAt;

}
