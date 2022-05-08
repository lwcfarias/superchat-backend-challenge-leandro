package com.superchat.messaging.model.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String email;
	
	private String companyName;
	
	@OneToMany(mappedBy = "customer", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Contact> contacts;	
}
