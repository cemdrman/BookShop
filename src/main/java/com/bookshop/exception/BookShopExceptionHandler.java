package com.bookshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bookshop.dto.response.ResponseBuilder;
import com.bookshop.dto.response.ServiceReponse;

@ControllerAdvice
public class BookShopExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final <T> ServiceReponse<T> handle(Exception ex, WebRequest request) {
		return ResponseBuilder.failure(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookShopException.class)
	public final <T> ServiceReponse<T> handle(BookShopException ex, WebRequest request) {
		return ResponseBuilder.failure(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoginFailedException.class)
	public final <T> ServiceReponse<T> handle(LoginFailedException ex, WebRequest request) {
		return ResponseBuilder.failure(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

}
