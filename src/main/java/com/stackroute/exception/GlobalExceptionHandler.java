package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(BlogAlreadyExistsException.class)
	public ResponseEntity<Object> handleBlogAlreadyExistsException(BlogAlreadyExistsException exception){
		  return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Blog Already Exists");
	}
	@ExceptionHandler(BlogNotFoundException.class)
	public ResponseEntity<Object> BlogNotFoundException(BlogNotFoundException exception){
		  return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Blog Not Found");
	}
	 @ExceptionHandler({Exception.class})
	    public ResponseEntity<Object> handleException(Exception exception) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(exception.getMessage());
	    }
}
