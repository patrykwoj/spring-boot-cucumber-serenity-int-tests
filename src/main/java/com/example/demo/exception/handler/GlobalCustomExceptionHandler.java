package com.example.demo.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.OrderException;
import com.example.demo.exception.ProductException;

@ControllerAdvice
public class GlobalCustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalCustomExceptionHandler.class); 
	
	@ExceptionHandler(OrderException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public void handleServerException(OrderException ex, HttpServletRequest request) {
		String originalUri = request.getRequestURI();
		LOGGER.error("Error from endpoint: " + originalUri + " Msg: " + ex.getMessage());
	}
	
	@ExceptionHandler(ProductException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public void handleServerException(ProductException ex, HttpServletRequest request) {
		String originalUri = request.getRequestURI();
		LOGGER.error("Error from endpoint: " + originalUri + " Msg: " + ex.getMessage());
	}
	

	
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public void handleServerException(IOException ex, HttpServletRequest request) {
		LOGGER.error("Error while loading product json file. ", ex);
	}
}