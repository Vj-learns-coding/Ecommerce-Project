package com.ecommerce.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.model.Product;
import com.ecommerce.app.payload.ProductDTO;
import com.ecommerce.app.payload.ProductResponse;

public interface ProductService {

	ProductDTO addProduct(long categoryId,ProductDTO productDTO);
	ProductResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortOrder);
	ProductResponse getProductsFromCategory(long categoryId, int pageNumber, int pageSize, String sortBy, String sortOrder);
	ProductResponse getPoductsByName(String keyword, int pageNumber, int pageSize, String sortBy, String sortOrder);
	ProductDTO updateProduct(long productId, ProductDTO productDTO);
	void deleteProduct(long productId);
	ProductDTO updateProductImage(long productId, MultipartFile image) throws IOException;

}
