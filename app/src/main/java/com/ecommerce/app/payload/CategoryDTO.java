package com.ecommerce.app.payload;

public class CategoryDTO {

	private String categoryName;
	private int categoryId;
	
	public CategoryDTO(String categoryName, int categoryId) {
		super();
		this.categoryName = categoryName;
		this.categoryId = categoryId;
	}
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
