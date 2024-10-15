package com.ecommerce.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.app.payload.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // validations exceptions come here.
	public ResponseEntity<Map<String,String>> methodArgumentValidatorExcetion(MethodArgumentNotValidException e) {
		Map<String,String> response = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<APIResponse> methodException(Exception e){
//		APIResponse response = new APIResponse(e.getMessage());
//		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> methodResourceNotFoundException(ResourceNotFoundException e){
		APIResponse response = new APIResponse(e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<APIResponse> methodApiException(ApiException e){
		APIResponse response = new APIResponse(e.getMessage());
	return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

	
		
	}
//
}
