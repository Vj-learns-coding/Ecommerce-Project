package com.ecommerce.app.payload;

import java.util.List;

public class CategoryResponse {
	
	List<CategoryDTO> categories;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalElements;
	private boolean lastPage;
	
	public CategoryResponse(List<CategoryDTO> categories) {
		super();
		this.categories = categories;
	}

	public CategoryResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	

	
	public List<CategoryDTO> getCategories() {
		return categories;
	}


	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}



	

}
