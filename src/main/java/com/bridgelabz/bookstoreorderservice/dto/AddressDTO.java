package com.bridgelabz.bookstoreorderservice.dto;

import lombok.Data;

/**
 * Purpose:DTO for address service
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Data
public class AddressDTO {
	public String name;
	public long phoneNumber;
	public long pincode; 
	public String locality;
	public String address;
	private String city;
	private String landmark;
}
