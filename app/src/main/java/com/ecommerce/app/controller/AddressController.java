package com.ecommerce.app.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.payload.AddressDTO;
import com.ecommerce.app.service.AddressService;

@RestController
@RequestMapping("/api")
public class AddressController {
	

	@Autowired
	AddressService addressService;
	
	@PostMapping("/addresses")
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDto){
		
		System.out.println("address dto received"+addressDto.getState());
		System.out.println("address dto received"+addressDto.getAddressId());
		System.out.println("address dto received"+addressDto.getCity());
		System.out.println("address dto received"+addressDto.getBuildingName());
		System.out.println("address dto received"+addressDto.getStreetName());
		System.out.println("address dto received"+addressDto.getPincode());
		System.out.println("address dto received"+addressDto.getCountry());
		
		
		AddressDTO savedAddressDto = addressService.createAddress(addressDto);
		
		return new ResponseEntity<>(savedAddressDto,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/addresses")
	public ResponseEntity<List<AddressDTO>> getAddress(){
		List<AddressDTO> addresses = addressService.getAddress();
		return new ResponseEntity<>(addresses,HttpStatus.OK);
	}
	
	@GetMapping("/addresses/{addressId}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable int addressId){
		
			AddressDTO savedAddressDto = addressService.getAddressById(addressId);
		
		return new ResponseEntity<>(savedAddressDto,HttpStatus.OK);
	}
	

	@GetMapping("/user/addresses")
	public ResponseEntity<List<AddressDTO>> getUserAddresses(){
		
		List<AddressDTO> addresses = addressService.getUserAddresses();
		
		return new ResponseEntity<>(addresses,HttpStatus.OK);
	}
	
	@PutMapping("/addresses/{addressId}")
	public ResponseEntity<AddressDTO> UpdateAddressById(@PathVariable int addressId,@RequestBody AddressDTO address){
		
			AddressDTO savedAddressDto = addressService.UpdateAddressById(addressId,address);
		
		return new ResponseEntity<>(savedAddressDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/addresses/{addressId}")
	public ResponseEntity<String> deleteAddressById(@PathVariable int addressId){
		
			String response = addressService.deleteAddressById(addressId);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	

}
