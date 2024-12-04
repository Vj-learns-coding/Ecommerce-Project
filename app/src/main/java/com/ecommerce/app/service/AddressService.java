package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.payload.AddressDTO;

public interface AddressService {
	
	AddressDTO createAddress(AddressDTO addressDto);
	List<AddressDTO> getAddress();
	AddressDTO getAddressById(int addressId);
	List<AddressDTO> getUserAddresses();
	AddressDTO UpdateAddressById(int addressId, AddressDTO address);
	String deleteAddressById(int addressId);
}
