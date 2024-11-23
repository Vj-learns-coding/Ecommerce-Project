package com.ecommerce.app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cartId;
	
	private double totalPrice;
	
	@OneToMany(mappedBy="cart",cascade= {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},orphanRemoval=true)
	private List<CartItem> cartItems = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="User_id")
	private User user;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(long cartId, double totalPrice, User user) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.user = user;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	

}
