package com.ecommerce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.app.config.AppConstants;
import com.ecommerce.app.payload.CategoryDTO;
import com.ecommerce.app.payload.CategoryResponse;
import com.ecommerce.app.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
		
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	@GetMapping("/public/categories")
	public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam (name="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
															 @RequestParam (name="pageSize" ,defaultValue=AppConstants.PAGE_SIZE,required=false)int pageSize,
															 @RequestParam (name="sortBy" ,defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
															 @RequestParam (name="sortOrder" ,defaultValue=AppConstants.SORT_ORDER,required=false)String sortOrder){
		CategoryResponse categories = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	
	@PostMapping("/admin/category")
	public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO category) {
		//if some of the fields of category are not there, then null will be assigned.
		categoryService.createCategory(category);
		return new ResponseEntity<>("category Added",HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/admin/categories/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") long id) {
		
			String response = categoryService.deleteCategory(id);
			return new ResponseEntity<>(response,HttpStatus.OK);
		
		
	}
	
	@PutMapping("/admin/categories/{id}")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO category,@PathVariable long id){
		try {
			String response = categoryService.updateCategory(category,id);
			return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
		}catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(),HttpStatus.NOT_FOUND);
		}
	}
	
	
}
