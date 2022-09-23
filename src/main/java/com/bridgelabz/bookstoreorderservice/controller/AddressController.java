package com.bridgelabz.bookstoreorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreorderservice.dto.AddressDTO;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.service.IAddressService;
import com.bridgelabz.bookstoreorderservice.util.OrderResponse;

/**
 * Purpose:Controller for order service
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	IAddressService addressService;
	
	/*
     *@Purpose : To add address
     *@Param : token
     */
	
	@PostMapping("/addAddress")
	public ResponseEntity<OrderResponse> addAddress(@RequestBody AddressDTO addressDTO, @RequestHeader String token) {
		AddressModel addressModel = addressService.addAddress(addressDTO, token);
		OrderResponse orderResponse = new OrderResponse(200, "Address added", addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : To update address
     *@Param : token and address id
     */
	
	@PutMapping("/updateAddress/{addressId}")
	public ResponseEntity<OrderResponse> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO, @RequestHeader String token) {
		AddressModel addressModel = addressService.updateAddress(addressId,addressDTO,token);
		OrderResponse orderResponse = new OrderResponse(200, "Address updated successfully", addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : To fetch all addresses
     *@Param : token
     */
	
	@GetMapping("/fetchAllAddress")
	public ResponseEntity<OrderResponse> fetchAllAddresses(@RequestHeader String token) {
		List<AddressModel> addressModel = addressService.fetchAllAddresses(token);
		OrderResponse orderResponse = new OrderResponse(200, "All addresses fetched", addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : To fetch address by id
     *@Param : token and address id
     */
	
	@GetMapping("/getAddress/{addressId}")
	public ResponseEntity<OrderResponse> getAddressById(@PathVariable Long addressId, @RequestHeader String token) {
		AddressModel addressModel = addressService.getAddressById(addressId,token);
		OrderResponse orderResponse = new OrderResponse(200, "Address fetched successfully", addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	
	/*
     *@Purpose : To delete address
     *@Param : token and address id
     */
	
	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<OrderResponse> deleteAddress(@PathVariable Long addressId, @RequestHeader String token) {
		AddressModel addressModel = addressService.deleteAddress(addressId, token);
		OrderResponse orderResponse = new OrderResponse(200, "Successfull", addressModel);
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}
}
