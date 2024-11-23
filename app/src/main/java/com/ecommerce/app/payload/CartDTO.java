package com.ecommerce.app.payload;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.model.Product;

public class CartDTO {
	 
	private Long cartId;
	private Double totalPrice=0.0;
	private List<ProductDTO> products = new ArrayList<>();
	
	
	public CartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDTO(Long cartId, Double totalPrice, List<ProductDTO> products) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.products = products;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
	
	
}
