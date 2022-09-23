package com.bridgelabz.bookstoreorderservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstoreorderservice.dto.AddressDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose:Model for address service
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
	public String name;
	public long phoneNumber;
	public long pincode; 
	public String locality;
	public String address;
	private String city;
	private String landmark;
	private long userId;
	
	public AddressModel(AddressDTO addressDTO) {
		this.name = addressDTO.getName();
		this.phoneNumber = addressDTO.getPhoneNumber();
		this.pincode = addressDTO.getPincode();
		this.locality = addressDTO.getLocality();
		this.address = addressDTO.getAddress();
		this.city = addressDTO.getCity();
		this.landmark = addressDTO.getLandmark();
	}
}
