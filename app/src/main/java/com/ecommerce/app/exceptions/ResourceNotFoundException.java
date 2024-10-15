package com.ecommerce.app.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	 
	
	String resourceName;          // is it category,product or address
	String field;                 //which field is missing category id or product name or address pin code

	String fieldName;              // like actual product name- dove soap, category name - soaps.
	Long   fieldId;               // like 1,2121,1211 actual id's of products or categories.
	
	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
		super(String.format("%s with %s %d is not found",resourceName,field,fieldId));
		this.fieldId = fieldId;
		this.resourceName=resourceName;
		this.field=field;
	}
	
	public ResourceNotFoundException(String resourceName, String field, String fieldName) {
		super(String.format("%s with %s %s is not found",resourceName,field,fieldName));
		this. fieldName = fieldName;
		this.resourceName=resourceName;
		this.field=field;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	
	
	
	
	
	
	
	
	

	
	
}
