package com.bridgelabz.bookstoreorderservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class OrderNotFoundException extends RuntimeException {
	private int statuscode;
	private String message;

	public OrderNotFoundException(int statuscode, String message) {
		super(message);
		this.statuscode = statuscode;
		this.message = message;
	}
}
