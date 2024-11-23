package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
		@Query("Select c from Cart c where c.user.email = ?1")
		Cart findCartByEmail(String email);

		@Query("Select c from Cart c where c.cartId = ?2 and c.user.email = ?1 ")
		Cart findCartByEmailIdAndCartId(String email,long cartId);
		
//		@Query("select c from Cart c where c.user.email=?1 and c.cartId=?2")
//		Cart findCartByEmailAndCart(String email,long cartId);
}
