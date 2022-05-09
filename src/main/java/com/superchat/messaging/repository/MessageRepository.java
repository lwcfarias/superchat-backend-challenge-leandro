package com.superchat.messaging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superchat.messaging.model.entity.MessageHistory;

public interface MessageRepository extends JpaRepository<MessageHistory, Long> {
	List<MessageHistory> findAllBySenderAndCompanyName(String sender, String companyName);
}
