package com.ecommerce.app.service;


import com.ecommerce.app.payload.CategoryDTO;
import com.ecommerce.app.payload.CategoryResponse;


public interface CategoryService {
	 CategoryResponse getAllCategories(int pageNumber,int pageSize, String sortBy, String sortOrder);
	 void createCategory(CategoryDTO categroy);
	 String deleteCategory(long id);
	 String updateCategory(CategoryDTO category, long id);
}
