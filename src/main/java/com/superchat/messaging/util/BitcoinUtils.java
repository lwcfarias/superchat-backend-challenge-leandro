package com.superchat.messaging.util;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class BitcoinUtils {
	private static final String BITCOIN_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	private BitcoinUtils() {}
	
	private static String getBitcoinData() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(BITCOIN_URL, String.class);
	}

	private static String parseBitcoinPrice(String str) {
		JSONObject jsonObject = new JSONObject(str);
		Float currentPrice = jsonObject.getJSONObject("bpi").getJSONObject("USD").getFloat("rate_float");
		return String.valueOf(currentPrice);
	}
	
	public static String currentBitcoinPrice() {
		String bitcoinJsonData = getBitcoinData();
		return parseBitcoinPrice(bitcoinJsonData);
	}
}
