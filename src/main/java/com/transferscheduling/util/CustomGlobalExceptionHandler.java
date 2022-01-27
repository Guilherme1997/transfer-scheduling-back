package com.transferscheduling.util;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)

    public ResponseEntity<?> handleAnyUncaughtException(Exception e) {
    	
    	System.out.println("Erro:" + e.getMessage());
    	  
    	var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    	
        return new ResponseEntity<>(customError(e.getMessage(),statusCode.value() ),statusCode);	
    	
    }
    
    private CustomErrorResponse customError(String menssager, int statusCode) 
    {
  	  CustomErrorResponse errors = new CustomErrorResponse();
	  
      errors.setTimestamp(LocalDateTime.now());
      errors.setError(menssager);
      errors.setStatus(statusCode);
      return errors;
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                  HttpHeaders headers, HttpStatus status, WebRequest request) {
           
    	   System.out.println("Erro:" + ex.getMessage());
    	               
           var statusCode = HttpStatus.BAD_REQUEST;
           
           var custom = customError(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),statusCode.value());

           custom.setValidationList(ex.getBindingResult()
        		   .getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage())
        		   .collect(Collectors.toList()));
           
          return new ResponseEntity<>(custom, HttpStatus.BAD_REQUEST);
    }
    

}
