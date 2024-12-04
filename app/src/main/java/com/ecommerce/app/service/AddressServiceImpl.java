package com.ecommerce.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.exceptions.ResourceNotFoundException;
import com.ecommerce.app.model.Address;
import com.ecommerce.app.model.User;
import com.ecommerce.app.payload.AddressDTO;
import com.ecommerce.app.repository.AddressRepository;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.utils.AuthUtils;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthUtils authUtils;
	
	@Autowired
	AddressRepository addressRepository;

	public AddressDTO createAddress(AddressDTO addressDto){
		
			Address address = modelMapper.map(addressDto, Address.class);
		
			User user = authUtils.loggedInUser();
		
			List<Address> addresses = user.getAddresses();
			addresses.add(address);
			user.setAddresses(addresses);
			
			address.setUsers(user);
			
			Address savedAddress= addressRepository.save(address);
			
			AddressDTO savedAddressDto = modelMapper.map(savedAddress, AddressDTO.class);
			
			return savedAddressDto;
		
	}

	@Override
	public List<AddressDTO> getAddress() {
		// TODO Auto-generated method stub
		List<Address> addresses = addressRepository.findAll();
		List<AddressDTO> addressDTOs= addresses.stream()
										.map(address -> modelMapper.map(address, AddressDTO.class))
										.collect(Collectors.toList());
		return addressDTOs;
	}

	@Override
	public AddressDTO getAddressById(int addressId) {
		Address address;
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		
		if(optionalAddress.isPresent()) {
			 address = optionalAddress.get();
		}else {
			throw new ResourceNotFoundException("Address","AddressId",(long)addressId);
		}
		
		AddressDTO addressDto = modelMapper.map(address, AddressDTO.class);
		return addressDto;
	
	}

	@Override
	public List<AddressDTO> getUserAddresses() {
		User user = authUtils.loggedInUser();
		List<Address> addresses = addressRepository.findAddressByUser(user.getUserId()); 
		List<AddressDTO> addressDTOs= addresses.stream()
				.map(address -> modelMapper.map(address, AddressDTO.class))
				.collect(Collectors.toList());
		return addressDTOs;
		
	}

	@Override
	public AddressDTO UpdateAddressById(int addressId, AddressDTO addressDto) {
		Address address;
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		
		if(optionalAddress.isPresent()) {
			 address = optionalAddress.get();
		}else {
			throw new ResourceNotFoundException("Address","AddressId",(long)addressId);
		}
		
		User user = authUtils.loggedInUser();
		user.getAddresses().remove(address);
		
		address.setBuildingName(addressDto.getBuildingName());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setPincode(addressDto.getPincode());
		address.setState(addressDto.getState());
		address.setStreetName(addressDto.getStreetName());
		
		user.getAddresses().add(address);
		
		Address savedAddress = addressRepository.save(address);
		
		AddressDTO savedAddressDto = modelMapper.map(savedAddress, AddressDTO.class);
		
		return savedAddressDto;
	}

	@Override
	public String deleteAddressById(int addressId) {
		Address address;
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		
		if(optionalAddress.isPresent()) {
			 address = optionalAddress.get();
		}else {
			throw new ResourceNotFoundException("Address","AddressId",(long)addressId);
		}
		
		User user=authUtils.loggedInUser();
		user.getAddresses().remove(address);
		userRepository.save(user);
		
		addressRepository.delete(address);
		
		return "address is deleted";
	}
}
