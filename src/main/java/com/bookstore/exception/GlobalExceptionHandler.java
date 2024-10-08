package com.bookstore.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public HashMap<String, String> exceptionHandler(BookNotFoundException bookNotFoundException) {
		
		HashMap<String, String> error = new HashMap<>();
		
//		logger.info(HttpStatus.BAD_REQUEST.toString().substring(0,3));
		
		error.put("errorCode", HttpStatus.BAD_REQUEST.toString().substring(0,3));
		error.put("errorMessage", bookNotFoundException.getMessage());
		
		
		return error;
	}
	
	@ResponseBody
	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public Map<String, String> bookExistsException(BookAlreadyExistsException bookAlreadyExistsException) {
		 Map<String, String> error = new HashMap<>();
		 
		 error.put("errorCode", HttpStatus.CONFLICT.toString().substring(0,3));
		 error.put("errorMessage", bookAlreadyExistsException.getMessage());
		 
		 return error;
	}

}
