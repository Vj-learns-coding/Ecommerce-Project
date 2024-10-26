package com.ecommerce.app.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Address")
@ToString
public class Address {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	
	@NotBlank
	@Size(min=5,message="street name should be more than 5 characters")
	private String streetName;
	
	@NotBlank
	@Size(min=5,message="building name should be more than 5 characters")
	private String buildingName;
	
	@NotBlank
	@Size(min=5,message="city name should be more than 5 characters")
	private String city;
	
	@NotBlank
	@Size(min=2,message="state name should be more than 2 characters")
	private String State;
	
	@NotBlank
	@Size(min=5,message="country name should be more than 5 characters")
	private String country;
	
	@NotBlank
	@Size(min=6,message="pincode should be more than 5 characters")
	private String pincode;
	
	@ToString.Exclude
	@ManyToMany(mappedBy="addresses")
	private Set<User> users = new HashSet<>();// should be a list.

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Address(@NotBlank @Size(min = 5, message = "street name should be more than 5 characters") String streetName,
			@NotBlank @Size(min = 5, message = "building name should be more than 5 characters") String buildingName,
			@NotBlank @Size(min = 5, message = "city name should be more than 5 characters") String city,
			@NotBlank @Size(min = 2, message = "state name should be more than 2 characters") String state,
			@NotBlank @Size(min = 5, message = "country name should be more than 5 characters") String country,
			@NotBlank @Size(min = 6, message = "pincode should be more than 5 characters") String pincode,
			Set<User> users) {
		super();
		this.streetName = streetName;
		this.buildingName = buildingName;
		this.city = city;
		State = state;
		this.country = country;
		this.pincode = pincode;
		this.users = users;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
