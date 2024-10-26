package com.ecommerce.app.model;



import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Users",uniqueConstraints= {
		@UniqueConstraint(columnNames="userName"),
		@UniqueConstraint(columnNames="email")
	})

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank
	@Size(max=20)
	private String userName;
	
	@NotBlank
	@Size(max=50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max=150)
	private String password;
	
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="user_address",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="address_id"))
	private Set<Address> addresses = new HashSet<>();
	
	
	@OneToMany(mappedBy="user",cascade= {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval=true)
	private Set<Product> prodcuts ;// why no initiallization.


	public User(@NotBlank @Size(max = 20) String userName, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 150) String password, Set<Role> roles, Set<Address> addresses,
			Set<Product> prodcuts) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.addresses = addresses;
		this.prodcuts = prodcuts;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Set<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}


	public Set<Product> getProdcuts() {
		return prodcuts;
	}


	public void setProdcuts(Set<Product> prodcuts) {
		this.prodcuts = prodcuts;
	}




}
