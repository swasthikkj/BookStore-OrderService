package com.bridgelabz.bookstoreorderservice.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstoreorderservice.exception.OrderNotFoundException;
import com.bridgelabz.bookstoreorderservice.util.OrderResponse;
@ControllerAdvice
public class OrderExceptionHandler {
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<OrderResponse> handleId(OrderNotFoundException ab) {
		OrderResponse response = new OrderResponse();
		response.setErrorcode(400);
		response.setMessage(ab.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
