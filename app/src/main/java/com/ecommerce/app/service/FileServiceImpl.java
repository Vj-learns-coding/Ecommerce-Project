package com.ecommerce.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// get original file name
		
		String originalFileName=file.getOriginalFilename();
		
		//get an uuid
		
		String uuid= UUID.randomUUID().toString();
		
		// change the name to uuid.extension
		String fileExtension= originalFileName.substring(originalFileName.lastIndexOf('.'));
		
		String fileName=uuid.concat(fileExtension);
		
		// get the filepath.
		String filePath=path+File.separator+fileName;
		
		// check if folder exists otherwise create it.
		File folder = new File(path);
		
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		// add the filepath
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return fileName;
		
		
		
	}
}
