package com.ecommerce.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cartItemsId;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	private int quantity;
	
	private double discount;
	
	private double productPrice;
	
	public CartItem(long cartItemsId, Cart cart, Product product, int quantity, double discount, double productPrice) {
		super();
		this.cartItemsId = cartItemsId;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.discount = discount;
		this.productPrice = productPrice;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCartItemsId() {
		return cartItemsId;
	}

	public void setCartItemsId(long cartItemsId) {
		this.cartItemsId = cartItemsId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	
	
	
	
	

}
