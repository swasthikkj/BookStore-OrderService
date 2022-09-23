package com.bridgelabz.bookstoreorderservice.util;

import com.bridgelabz.bookstoreorderservice.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
	private int errorcode;
	private String message;
	private UserDTO token;
	
	public UserResponse() {
		
	}
}
