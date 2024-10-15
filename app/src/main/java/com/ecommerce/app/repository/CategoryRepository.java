package com.ecommerce.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.Category;


public interface CategoryRepository extends JpaRepository<Category,Long>{
	public Category findByCategoryName(String categoryName);
}
