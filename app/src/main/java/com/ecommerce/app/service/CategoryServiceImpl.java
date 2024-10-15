package com.ecommerce.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.app.exceptions.ApiException;
import com.ecommerce.app.exceptions.ResourceNotFoundException;
import com.ecommerce.app.model.Category;
import com.ecommerce.app.payload.CategoryDTO;
import com.ecommerce.app.payload.CategoryResponse;
import com.ecommerce.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryResponse getAllCategories(int pageNumber,int pageSize, String sortBy, String sortOrder) {
		
		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? 
							  Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageDetails = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
		Page<Category> categoryPage = categoryRepo.findAll(pageDetails);
		List<Category> categories = categoryPage.getContent();
		
		if(categories.isEmpty()) {
			throw new ApiException("No data Present");
		}
		
		List<CategoryDTO> categoryDTOS= categories.stream()
												  .map(category->modelMapper.map(category,CategoryDTO.class))
												  .toList();
		CategoryResponse categoryResponse = new CategoryResponse(categoryDTOS);
		categoryResponse.setPageNumber(categoryPage.getNumber());
		categoryResponse.setPageSize(categoryPage.getSize());
		categoryResponse.setTotalElements(categoryPage.getTotalElements());
		categoryResponse.setTotalPages(categoryPage.getTotalPages());
		categoryResponse.setLastPage(categoryPage.isLast());
		return categoryResponse;
	}

	@Override
	public void createCategory(CategoryDTO categoryDTO) {
		
		Category categoryEntity = modelMapper.map(categoryDTO, Category.class);
				
		 Category existingCategory = categoryRepo.findByCategoryName(categoryEntity.getCategoryName());
		 if(existingCategory!=null) {
			 throw new ApiException("Category with name "+categoryDTO.getCategoryName()+" already exists");
		 }
		categoryRepo.save(categoryEntity);

	}
	
	@Override
	public String deleteCategory(long id)  {
		
//
		
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		if(optionalCategory.isPresent()) {
			categoryRepo.delete(optionalCategory.get());
			return "Category deleted"; 
		}else {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found");
			throw new ResourceNotFoundException("Category","Category id",id);
		}
			
	}

	@Override
	public String updateCategory(CategoryDTO categoryDTO, long id) {
//	
		Category category = modelMapper.map(categoryDTO, Category.class);
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		if(optionalCategory.isPresent()) {
			Category existingCategory = optionalCategory.get();
			existingCategory.setCategoryName(category.getCategoryName());
			categoryRepo.save(existingCategory);
			return  "Category with id "+id+" is updated";
		}else {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found");
			throw new ResourceNotFoundException("Category","Category id",id);
		}

							
	}

}
