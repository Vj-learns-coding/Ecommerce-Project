package com.ecommerce.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity

public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long productId;
	
	private double discount;
	private String productName;
	private String description;
	private int quantity;
	private double price;
	private double specialPrice;
	private String image;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private User user;

	

	public Product(double discount, String productName, String description, int quantity, double price,
			double specialPrice, String image, Category category, User user) {
		super();
		this.discount = discount;
		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.specialPrice = specialPrice;
		this.image = image;
		this.category = category;
		this.user = user;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	

}
