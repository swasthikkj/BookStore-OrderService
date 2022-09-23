package com.bridgelabz.bookstoreorderservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
	private int errorcode;
	private String message;
	private Object token;
	
	public OrderResponse() {
		
	}
}
