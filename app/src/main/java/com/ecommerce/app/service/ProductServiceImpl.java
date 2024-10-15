package com.ecommerce.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.exceptions.ApiException;
import com.ecommerce.app.exceptions.ResourceNotFoundException;
import com.ecommerce.app.model.Category;
import com.ecommerce.app.model.Product;

import com.ecommerce.app.payload.ProductDTO;
import com.ecommerce.app.payload.ProductResponse;
import com.ecommerce.app.repository.CategoryRepository;
import com.ecommerce.app.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.name}")
	private String path;

	@Override
	public ProductDTO addProduct(long categoryId, ProductDTO productDTO) {
		
		Category existingCategory = categoryRepository.findById(categoryId)
											  .orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId));
		Product product = modelMapper.map(productDTO, Product.class);
		product.setCategory(existingCategory);
		
		double specialPrice = product.getPrice()*(1-(product.getDiscount()*0.01));
		product.setSpecialPrice(specialPrice);
		
		
		
		productRepository.save(product);
		
		return modelMapper.map(product,ProductDTO.class);
		
	}

	@Override
	public ProductResponse getAllProducts(int pageNumber,int pageSize,String sortBy,String sortOrder ) {
		
		Sort soryByAndOrder = sortOrder.equalsIgnoreCase("asc")?
							  Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, soryByAndOrder);
		
		Page<Product> productPage= productRepository.findAll(pageDetails);
		List<Product> products = productPage.getContent();
		
		if(products.isEmpty()) {
			throw new ApiException("Products Has not been added Yet");
		}
		
		List<ProductDTO> productDTOS = products.stream()
											   .map(product -> modelMapper.map(product,ProductDTO.class))
											   .toList();
		ProductResponse productResponse = new ProductResponse(productDTOS);
		productResponse.setPageNumber(productPage.getNumber());
		productResponse.setPageSize(productPage.getSize());
		productResponse.setTotalElements(productPage.getTotalElements());
		productResponse.setTotalPages(productPage.getTotalPages());
		productResponse.setLastPage(productPage.isLast());
		return productResponse;
	}

	@Override
	public ProductResponse getProductsFromCategory(long categoryId,int pageNumber,int pageSize,String sortBy,String sortOrder) {
		
		//check for category
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if(optionalCategory.isEmpty()) {
			throw new ResourceNotFoundException("Category","Category id",categoryId);
		}
		
		Sort soryByAndOrder = sortOrder.equalsIgnoreCase("asc")?
				  Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, soryByAndOrder);
		
		//check for the products
		Page<Product> productPage= productRepository.findByCategoryOrderByPriceAsc(optionalCategory.get(),pageDetails);
		List<Product> products = productPage.getContent();
		if(products.isEmpty()) {
			throw new ApiException("There aren't any proudcts in that category");
		}
		
		//send them
		List<ProductDTO> productDTOS = products.stream()
				   .map(product -> modelMapper.map(product,ProductDTO.class))
				   .toList();
		
		
		ProductResponse productResponse = new ProductResponse(productDTOS);
		productResponse.setPageNumber(productPage.getNumber());
		productResponse.setPageSize(productPage.getSize());
		productResponse.setTotalElements(productPage.getTotalElements());
		productResponse.setTotalPages(productPage.getTotalPages());
		productResponse.setLastPage(productPage.isLast());
		
		return productResponse;	
	}

	@Override
	public ProductResponse getPoductsByName(String keyword, int pageNumber, int pageSize, String sortBy, String sortOrder) {
		
		Sort soryByAndOrder = sortOrder.equalsIgnoreCase("asc")?
				  Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, soryByAndOrder);
		
		//check for the products
		Page<Product> productPage = productRepository.findByProductNameLikeIgnoreCase("%"+keyword+"%",pageDetails);
		List<Product> products = productPage.getContent();
		if(products.isEmpty()) {
			throw new ApiException("There aren't any products available with the name "+keyword);
		}
				
		//send them
		List<ProductDTO> productDTOS = products.stream()
				   .map(product -> modelMapper.map(product,ProductDTO.class))
				   .toList();
		ProductResponse productResponse = new ProductResponse(productDTOS);
		productResponse.setPageNumber(productPage.getNumber());
		productResponse.setPageSize(productPage.getSize());
		productResponse.setTotalElements(productPage.getTotalElements());
		productResponse.setTotalPages(productPage.getTotalPages());
		productResponse.setLastPage(productPage.isLast());
		
		return productResponse;
	}

	@Override
	public ProductDTO updateProduct(long productId, ProductDTO productDTO) {
		
		Optional<Product> existingProduct = productRepository.findById(productId);
		if(existingProduct.isEmpty()) {
			throw new ApiException("No proudct with id "+productId);
		}
		
		Product product = modelMapper.map(productDTO, Product.class);
		
		Product productFromDB = existingProduct.get();
		productFromDB.setProductName(product.getProductName());
		productFromDB.setDescription(product.getDescription());
		productFromDB.setQuantity(product.getQuantity());
		productFromDB.setPrice(product.getPrice());
		productFromDB.setDiscount(product.getDiscount());
		
		double specialPrice = product.getPrice()*(1-(product.getDiscount()/100));
		productFromDB.setSpecialPrice(specialPrice);
		
		productRepository.save(productFromDB);
		
		ProductDTO savedProduct = modelMapper.map(productFromDB, ProductDTO.class);

		return savedProduct;
	}

	@Override
	public void deleteProduct(long productId) {
		
		Optional<Product> existingProduct = productRepository.findById(productId);
		if(existingProduct.isEmpty()) {
			throw new ApiException("No proudct with id "+productId);
		}
		
		Product productFromDB = existingProduct.get();
		
		productRepository.delete(productFromDB);
		
	}

	@Override
	public ProductDTO updateProductImage(long productId, MultipartFile image) throws IOException {
	
		Optional<Product> existingProduct = productRepository.findById(productId);
		if(existingProduct.isEmpty()) {
			throw new ResourceNotFoundException("Proudct","ProductId",productId);
		}
		Product product = existingProduct.get();
		
		// to save a file to a server.
		
		// get image filename
		
		String fileName = fileService.uploadImage(path,image);
		// add it to product
		product.setImage(fileName);
		// save the product
		productRepository.save(product);
		// convert it to the productDTO
		ProductDTO savedProductDTO = modelMapper.map(product, ProductDTO.class);
		// return it
		return savedProductDTO;
	}
	
	
	
	

}
