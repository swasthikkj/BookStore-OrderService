package com.bridgelabz.bookstoreorderservice.service;

import java.util.List;

import com.bridgelabz.bookstoreorderservice.dto.AddressDTO;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;

/**
 * Purpose:Interface for address
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

public interface IAddressService {

	AddressModel addAddress(AddressDTO addressDTO, String token);

	AddressModel updateAddress(Long addressId, AddressDTO addressDTO, String token);

	List<AddressModel> fetchAllAddresses(String token);

	AddressModel getAddressById(Long addressId, String token);

	AddressModel deleteAddress(Long addressId, String token);

}
