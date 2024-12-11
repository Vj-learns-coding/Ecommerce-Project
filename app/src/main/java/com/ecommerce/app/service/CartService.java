package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.payload.CartDTO;

public interface CartService {
	CartDTO addProductToCart(long productId,int quantity);
	List<CartDTO> getAllCarts();
	CartDTO getUsersCart(String emailId,long cartId);
	CartDTO updateProductQuantity(long productId,int quantity);
	String deleteProductFromCart(long cartId,long productId);
}
