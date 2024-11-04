package com.ecommerce.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Roles")

public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private AppRole roleName;

	
//	 enum contains .name() method which  returns string of the variable
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(AppRole roleName) {
		super();
		this.roleName = roleName;
	}

	public AppRole getRoleName() {
		return roleName;
	}

	public void setRoleName(AppRole roleName) {
		this.roleName = roleName;
	}

	
	
}
