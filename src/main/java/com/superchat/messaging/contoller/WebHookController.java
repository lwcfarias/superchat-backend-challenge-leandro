package com.superchat.messaging.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superchat.messaging.model.dto.MessageResponse;
import com.superchat.messaging.model.dto.WebHookRequest;
import com.superchat.messaging.services.WebHookService;

@RestController
@RequestMapping("sendMessage")
public class WebHookController {
	@Autowired
	private WebHookService webHookService;
	
	@PostMapping
	public ResponseEntity<MessageResponse> sendMessage(@RequestBody WebHookRequest webHookRequest) {
		return ResponseEntity.ok(webHookService.sendMessage(webHookRequest));
	}
	
}
