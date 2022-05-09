package com.superchat.messaging.model.dto;

public enum PlaceHolders {
	CONTACT_NAME("RecipientName"),
	CURRENT_BITCOIN_PRICE("BitcoinPrice");
	
	private String placeholder;
	
	PlaceHolders(String placeholder) {
		this.placeholder = placeholder; 
	}
	
	public String value() {
		return this.placeholder;
	}
}
