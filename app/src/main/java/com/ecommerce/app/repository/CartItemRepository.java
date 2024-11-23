package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	//@Query("select ci from CartItem ci where ci.cart.cartId=?1 and ci.product.productId=?2")
//	CartItem findCartItemByCartIdAndProductId(long cartId, long productId);
//	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
    CartItem findCartItemByProductIdAndCartId(long cartId, long productId);

}
