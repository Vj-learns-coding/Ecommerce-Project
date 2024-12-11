package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.payload.AddressDTO;

public interface AddressService {
	
	AddressDTO createAddress(AddressDTO addressDto);
	List<AddressDTO> getAddress();
	AddressDTO getAddressById(long addressId);
	List<AddressDTO> getUserAddresses();
	AddressDTO UpdateAddressById(long addressId, AddressDTO address);
	String deleteAddressById(long addressId);
}
