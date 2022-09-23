package com.bridgelabz.bookstoreorderservice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
	private long userId;
	private String firstName; 
	private String lastName; 
	private String dateOfBirth;
	private LocalDate registeredDate;
	private LocalDate updatedDate;
	private String password;
	private String emailId;
	private boolean verify;
	private int otp;
	private String profilePic;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;

}
