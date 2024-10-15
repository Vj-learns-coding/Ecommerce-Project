package com.ecommerce.app.controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.config.AppConstants;
import com.ecommerce.app.payload.ProductDTO;
import com.ecommerce.app.payload.ProductResponse;
import com.ecommerce.app.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/admin/categories/{categoryId}/product")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO, @PathVariable long categoryId){
			ProductDTO savedProductDTO = productService.addProduct(categoryId,productDTO);	
			return new ResponseEntity<>(savedProductDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/public/products")
	public ResponseEntity<ProductResponse> getAllProducts(@RequestParam (name="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
														  @RequestParam (name="pageSize" ,defaultValue=AppConstants.PAGE_SIZE,required=false)int pageSize,
														  @RequestParam (name="sortBy" ,defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
														  @RequestParam (name="sortOrder" ,defaultValue=AppConstants.SORT_ORDER,required=false)String sortOrder){
		ProductResponse productResponse = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@GetMapping("/public/categories/{categoryId}/products")
	public ResponseEntity<ProductResponse> getProductsFromCategory(@PathVariable long categoryId,@RequestParam (name="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
																   @RequestParam (name="pageSize" ,defaultValue=AppConstants.PAGE_SIZE,required=false)int pageSize,
																   @RequestParam (name="sortBy" ,defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
																   @RequestParam (name="sortOrder" ,defaultValue=AppConstants.SORT_ORDER,required=false)String sortOrder){
		ProductResponse productResponse = productService.getProductsFromCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@GetMapping("/public/products/keyword/{keyword}")
	public ResponseEntity<ProductResponse> getProductsFromCategory(@PathVariable String keyword,@RequestParam (name="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) int pageNumber,
																   @RequestParam (name="pageSize" ,defaultValue=AppConstants.PAGE_SIZE,required=false)int pageSize,
																   @RequestParam (name="sortBy" ,defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
																   @RequestParam (name="sortOrder" ,defaultValue=AppConstants.SORT_ORDER,required=false)String sortOrder){
		ProductResponse productResponse = productService.getPoductsByName(keyword,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@PutMapping("/admin/products/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable long productId,@RequestBody ProductDTO productDTO){
		ProductDTO updatedProductDTO = productService.updateProduct(productId,productDTO);
		return new ResponseEntity<>(updatedProductDTO,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/admin/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable long productId){
		productService.deleteProduct(productId);
		return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
		
	}
	
	@PutMapping("/admin/products/{roductId}/image")
	public ResponseEntity<ProductDTO> updateProductImage(@PathVariable("roductId") long productId,@RequestParam("image") MultipartFile image) throws IOException{
		ProductDTO updatedProductDTO = productService.updateProductImage(productId,image);
		return new ResponseEntity<>(updatedProductDTO,HttpStatus.ACCEPTED);
		
	}
	

}
