package com.concentrix.project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ErrorDetails> CartExceptionHandler(CartException ce, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> CustomerExceptionHandler(CustomerException cue, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(cue.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ItemException.class)
	public ResponseEntity<ErrorDetails> ItemExceptionHandler(ItemException ie, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrdersException.class)
	public ResponseEntity<ErrorDetails> OrdersExceptionHandler(OrdersException oe, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(oe.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(RestaurantException.class)
	public ResponseEntity<ErrorDetails> RestaurantExceptionHandler(RestaurantException re, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(re.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ex, WebRequest wr){
		ErrorDetails err = new ErrorDetails();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
