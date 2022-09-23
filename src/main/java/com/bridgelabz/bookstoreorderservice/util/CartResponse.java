package com.bridgelabz.bookstoreorderservice.util;

import com.bridgelabz.bookstoreorderservice.dto.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponse {
	private int errorcode;
	private String message;
	private CartDTO token;
	
	public CartResponse() {
		
	}
}
