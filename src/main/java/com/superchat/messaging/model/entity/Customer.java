package com.superchat.messaging.model.entity;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "customer", uniqueConstraints={@UniqueConstraint(columnNames={"email", "companyName"})})
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String email;
	
	private String companyName;
	
	@OneToMany(mappedBy = "customer", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Contact> contacts;
	
	private OffsetDateTime creationAt;
}
