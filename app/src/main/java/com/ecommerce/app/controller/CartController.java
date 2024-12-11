package com.ecommerce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.model.Cart;
import com.ecommerce.app.payload.CartDTO;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.service.CartServiceImpl;
import com.ecommerce.app.utils.AuthUtils;

@RestController
@RequestMapping("/api/")
public class CartController {
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	AuthUtils authUtils;
	
	@PostMapping("/carts/products/{productId}/quantity/{quantity}")
	public ResponseEntity<CartDTO> addProductToCart(@PathVariable long productId,@PathVariable int quantity){
		CartDTO cartDto = cartServiceImpl.addProductToCart(productId,quantity);
		return new ResponseEntity<CartDTO>(cartDto,HttpStatus.CREATED);	
	}
	
	@GetMapping("/carts")
	public ResponseEntity<List<CartDTO>> getAllCarts(){
		List<CartDTO> cartDtos=cartServiceImpl.getAllCarts();
		return new ResponseEntity<>(cartDtos,HttpStatus.OK);	
	}
	
	@GetMapping("/carts/user/cart")
	public ResponseEntity<CartDTO> getUsersCart(){
		String email = authUtils.loggedInEmail();
		Cart cart = cartRepository.findCartByEmail(email);
		CartDTO cartDto = cartServiceImpl.getUsersCart(email,cart.getCartId());
		return new ResponseEntity<>(cartDto,HttpStatus.ACCEPTED);	
	}
	
	@PutMapping("/carts/products/{productId}/quantity/{operation}")
	public ResponseEntity<CartDTO> updateCartProduct(@PathVariable long productId,
													 @PathVariable String operation){
		
		int qunatity = operation.equals("delete")?-1:1;
		CartDTO cartDto = cartServiceImpl.updateProductQuantity(productId, qunatity);
		return new ResponseEntity<CartDTO>(cartDto,HttpStatus.ACCEPTED); 
		
	}
	
	@DeleteMapping("/carts/{cartId}/product/{productId}")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable long cartId,
													 @PathVariable long productId){
		String status = cartServiceImpl.deleteProductFromCart(cartId,productId);
		return new ResponseEntity<>(status,HttpStatus.OK);
		
	}

}
