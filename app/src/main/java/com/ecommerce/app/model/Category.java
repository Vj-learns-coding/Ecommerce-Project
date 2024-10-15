package com.ecommerce.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_generator")
	@SequenceGenerator(name="id_generator",allocationSize=1)
	private long categoryId;
	
	@NotBlank
	@Size(min=5,message="The size of this field should be atleast 5")
	private String categoryName;
	
	// jpa wont' use this constructor 
	public Category(long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	// jpa first creates a object using default constructor then adds the values
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long id) {
		this.categoryId = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
