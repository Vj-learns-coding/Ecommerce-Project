package com.ecommerce.app.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
	
	public String uploadImage(String path, MultipartFile file) throws IOException;
	
}
