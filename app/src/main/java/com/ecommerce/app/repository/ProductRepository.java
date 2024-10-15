package com.ecommerce.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.Category;
import com.ecommerce.app.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
		public Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);
		public Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
		
}
