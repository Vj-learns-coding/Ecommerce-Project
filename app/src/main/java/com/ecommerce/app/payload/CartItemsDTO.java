package com.ecommerce.app.payload;

public class CartItemsDTO {
	private Long cartItemId;
	private CartDTO cart;
	private ProductDTO productDTO;
	private Integer quantity;
	private Double discount;
	private Double productPrice;
	
	
	
	public CartItemsDTO(Long cartItemId, CartDTO cart, ProductDTO productDTO, Integer quantity, Double discount,
			Double productPrice) {
		super();
		this.cartItemId = cartItemId;
		this.cart = cart;
		this.productDTO = productDTO;
		this.quantity = quantity;
		this.discount = discount;
		this.productPrice = productPrice;
	}
	
	
	
	public CartItemsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	public CartDTO getCart() {
		return cart;
	}
	public void setCart(CartDTO cart) {
		this.cart = cart;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	
}
