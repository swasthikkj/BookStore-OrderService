package com.bridgelabz.bookstoreorderservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstoreorderservice.dto.AddressDTO;
import com.bridgelabz.bookstoreorderservice.exception.OrderNotFoundException;
import com.bridgelabz.bookstoreorderservice.model.AddressModel;
import com.bridgelabz.bookstoreorderservice.repository.AddressRepository;
import com.bridgelabz.bookstoreorderservice.util.TokenUtil;
import com.bridgelabz.bookstoreorderservice.util.UserResponse;

/**
 * Purpose:Service for address
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */

@Service
public class AddressService implements IAddressService {
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 *  Purpose:To add address
	 */
	
	@Override
	public AddressModel addAddress(AddressDTO addressDTO, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			AddressModel addressModel = new AddressModel(addressDTO);
			addressModel.setUserId(userId);
			addressRepository.save(addressModel);
			return addressModel;
		}
		throw new OrderNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:To update address
	 */
	
	@Override
	public AddressModel updateAddress(Long addressId, AddressDTO addressDTO, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
			if (isAddressPresent.isPresent()) {
				if (isAddressPresent.get().getUserId() == userId) {
					isAddressPresent.get().setName(addressDTO.getName());
					isAddressPresent.get().setPhoneNumber(addressDTO.getPhoneNumber());
					isAddressPresent.get().setAddress(addressDTO.getAddress());
					isAddressPresent.get().setPincode(addressDTO.getPincode());
					isAddressPresent.get().setLocality(addressDTO.getLocality());
					isAddressPresent.get().setLandmark(addressDTO.getLandmark());
					isAddressPresent.get().setCity(addressDTO.getCity());
					addressRepository.save(isAddressPresent.get());
					return isAddressPresent.get();
				}
				throw new OrderNotFoundException(500, "Invalid User");
			}
			throw new OrderNotFoundException(500, "Address Not Found");
		}
		throw new OrderNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:To fetch all addresses
	 */
	
	@Override
	public List<AddressModel> fetchAllAddresses(String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			List<AddressModel> isAddressPresent = addressRepository.findAll();
			if (isAddressPresent.size()>0) {
				return isAddressPresent;
			}
			throw new OrderNotFoundException(500, "Empty Address List");
		}
		throw new OrderNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:To fetch address by id
	 */
	
	@Override
	public AddressModel getAddressById(Long addressId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
			if (isAddressPresent.isPresent()) {
				if (isAddressPresent.get().getUserId() == userId) {
					return isAddressPresent.get();
				}
				throw new OrderNotFoundException(500, "Invalid User");
			}
			throw new OrderNotFoundException(500, "Address Not Found");
		}
		throw new OrderNotFoundException(500, "User Not Found");
	}

	/**
	 *  Purpose:To delete address
	 */
	
	@Override
	public AddressModel deleteAddress(Long addressId, String token) {
		Long userId = tokenUtil.decodeToken(token);
		UserResponse isUserPresent = restTemplate.getForObject("http://BS-UserService:8090/userService/verifyToken/" + userId, UserResponse.class);
		if (isUserPresent.getErrorcode() == 200) {
			Optional<AddressModel> isAddressPresent = addressRepository.findById(addressId);
			if (isAddressPresent.isPresent()) {
				if (isAddressPresent.get().getUserId() == userId) {
					addressRepository.delete(isAddressPresent.get());
					return isAddressPresent.get();
				}
			}
			throw new OrderNotFoundException(500, "Address Not Found");
		}
		throw new OrderNotFoundException(500, "User Not Found");

	}
}
