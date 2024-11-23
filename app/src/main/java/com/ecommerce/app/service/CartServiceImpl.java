package com.ecommerce.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.exceptions.ApiException;
import com.ecommerce.app.exceptions.ResourceNotFoundException;
import com.ecommerce.app.model.Cart;
import com.ecommerce.app.model.CartItem;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.payload.CartDTO;
import com.ecommerce.app.payload.ProductDTO;
import com.ecommerce.app.repository.CartItemRepository;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.utils.AuthUtils;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired 
	ModelMapper modelMapper;
	
	@Autowired
	AuthUtils authUtil;
	
	
	public CartDTO addProductToCart(long productId,int quantity) {
		
		Product product ;
		Cart cart;
		// check if cart is there, if not create it based on the user
				
		cart = createCart();
		
		// what if cart already contains that product as cartItem.
		
		CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cart.getCartId(),productId);
		
		if(cartItem!=null) {
			throw new ApiException("proudct already exist in the cart");
		}
		// fetch the product dto from product repository and create cartItem and add it list
				
		product = productRepository.findById(productId)
				.orElseThrow(()-> new ApiException("Proudct with id "+productId+" is not found"));
				
		if(product.getQuantity()==0) {
				throw new ApiException("Proudct is not available");
		}
				
		if(product.getQuantity()<quantity) {
				throw new ApiException("the available quantity of the proudct is only "+product.getQuantity());
		}
		else {
			product.setQuantity(product.getQuantity()-quantity);
			productRepository.save(product); // why product has not been saved.
		}
				
		
				
		cartItem=new CartItem();
		cartItem.setCart(cart);
		cartItem.setDiscount(product.getDiscount());
		cartItem.setProduct(product);
		cartItem.setProductPrice(product.getSpecialPrice());
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
		

		// change the price of the cart and add cart items to cart.
		
		List<CartItem> cartItems= cart.getCartItems();
		cartItems.add(cartItem);
		
		cart.setTotalPrice(cart.getTotalPrice()+(product.getSpecialPrice()*quantity));
		cart.setCartItems(cartItems);
		cartRepository.save(cart);
		
		// prepare cartDTO
		
		
		
		CartDTO cartDto = modelMapper.map(cart, CartDTO.class);
		List<ProductDTO> proudctDtos = cartItems.stream().map(item ->{ 
			
			ProductDTO productDto =modelMapper.map(item.getProduct(), ProductDTO.class);
			productDto.setQuantity(item.getQuantity());
			return productDto;
			
		}).collect(Collectors.toList());
		cartDto.setProducts(proudctDtos);
		return cartDto;
		
		
	}
	
	public Cart createCart() {
		// how to fetch the cart based on user-we have to know who logged in.
		// find cart bases on the user inside the cart = logged in user.
		// if found none ,create cart and give new one.
		Cart cart = cartRepository.findCartByEmail(authUtil.loggedInEmail());
		if(cart!=null) {
			return cart;
		}
		
		Cart newCart = new Cart();
		newCart.setTotalPrice(0.0);
		newCart.setUser(authUtil.loggedInUser());
		cartRepository.save(newCart);
		return newCart;
	}

	public List<CartDTO> getAllCarts() {
		
		List<Cart> carts = cartRepository.findAll();
		
		if(carts==null) {
			throw new ApiException("There aren't any carts");
		}
		
		List<CartDTO> cartDtos = carts.stream().map(cart -> {
						//step 1
								CartDTO cartDto = modelMapper.map(cart, CartDTO.class);
						//step 2
								List<ProductDTO> productDtos = 
								cart.getCartItems().stream()
								.map(item ->{ 
									
									ProductDTO productDto =modelMapper.map(item.getProduct(), ProductDTO.class);
									productDto.setQuantity(item.getQuantity());
									return productDto;
									
								})
								.collect(Collectors.toList());
						//step 3
								cartDto.setProducts(productDtos);
								return cartDto;
								
						}).collect(Collectors.toList());
	
		return cartDtos;
	}

	@Override
	public CartDTO getUsersCart(String emailId, long cartId) {
		System.out.println("the email id is ...................."+emailId.toString()+"-"+cartId);
		if(emailId==""||emailId==null) {
			throw new ApiException("email id not found");
		}
		Cart cart = cartRepository.findCartByEmailIdAndCartId(emailId,cartId);
		
		CartDTO cartDto = modelMapper.map(cart, CartDTO.class);
		
		List<ProductDTO> productDtos = cart.getCartItems().stream()
				.map(cartItem ->{
					ProductDTO productDto = modelMapper.map(cartItem.getProduct(),ProductDTO.class);
					productDto.setQuantity(cartItem.getQuantity());
					return productDto;
				})
				.collect(Collectors.toList());
		
		cartDto.setProducts(productDtos);
		
		return cartDto;
	}
	
	@Transactional
	@Override
	public CartDTO updateProductQuantity(long productId,int quantity) {

		Cart cart;
		Product product;
		//get cart of the user
		cart=cartRepository.findCartByEmail(authUtil.loggedInEmail());// it should be there
		
		// check if enough quantity of the product is there.
		product = productRepository.findById(productId)
				 .orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));
		
		if(product.getQuantity()==0) {
				throw new ApiException("Proudct is not available");
			}
			
		if(product.getQuantity()<quantity) {
				throw new ApiException("the available quantity of the proudct is only "+product.getQuantity());
			}
	
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product); // why product has not been saved.
		
		System.out.println("product id is "+productId+" and quantity is "+quantity+" cart id is "+cart.getCartId());
	
		// fetch the cartItem and update the quantity	
		CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cart.getCartId(),productId);
			
		if(cartItem==null) {
			throw new ApiException("proudct do not exist in the cart");
		}
		
		cartItem.setQuantity(cartItem.getQuantity()+quantity);
		
		cartItemRepository.save(cartItem);
		
		// update the price of the cart.
		cart.setTotalPrice(cart.getTotalPrice()+(quantity*cartItem.getProductPrice()));

		// if cartItem quantity becameZero delete it form db and cart.
		List<CartItem> cartItems= cart.getCartItems();
		if(cartItem.getQuantity()==0) {
			cartItemRepository.deleteById(cartItem.getCartItemsId());
			cartItems.remove(cartItem);
			cart.setCartItems(cartItems);
			
		}
		
		cartRepository.save(cart);
		
		
		// form a cartDTO.
		CartDTO cartDto = modelMapper.map(cart, CartDTO.class);
		List<ProductDTO> proudctDtos = cartItems.stream().map(item ->{ 
			
																		ProductDTO productDto =modelMapper.map(item.getProduct(), ProductDTO.class);
																		productDto.setQuantity(item.getQuantity());
																		return productDto;
																		
																	}).collect(Collectors.toList());
		cartDto.setProducts(proudctDtos);
		return cartDto;
		
		
	}

	@Override
	public String deleteProductFromCart(long cartId, long productId) {
		
		Cart cart;
		Product product;
		CartItem cartItem;
		
		cart = cartRepository.findById(cartId)
							 .orElseThrow(()-> new ResourceNotFoundException("cart","cartId",cartId));
		
		cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId, productId);
		
		if(cartItem==null) {
			throw new ApiException("proudct does not exist in the cart");
			}	
		product = productRepository.findById(productId)
								   .orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));
		
		//adjust the quantities and price of the cart.
		
		product.setQuantity(product.getQuantity()+cartItem.getQuantity());
		productRepository.save(product);
		
		cart.setTotalPrice(cart.getTotalPrice()-(cartItem.getQuantity()*product.getSpecialPrice()));
		
		// remove the cartItem for cart.
		
//		List<CartItem> cartItems = cart.getCartItems();
//		cartItems.remove(cartItem);
//		cart.setCartItems(cartItems);
		cartRepository.save(cart);
		
		//delete cartITem.
		
		cartItemRepository.delete(cartItem);
		
		return "Product has been deleted";
		
	}
}
