package com.bookshop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

	private ResponseBuilder() {

	}

	public static <T> ResponseEntity<ServiceReponse<T>> success(T data, HttpStatus status) {
		ServiceResponseHeader header = new ServiceResponseHeader(status, null);
		ServiceReponse<T> response = new ServiceReponse<>(header, data);
		return new ResponseEntity<>(response, status);
	}

	public static <T> ResponseEntity<ServiceReponse<T>> failure(String errorMessage, HttpStatus status) {
		ServiceResponseHeader header = new ServiceResponseHeader(status, errorMessage);
		ServiceReponse<T> response = new ServiceReponse<>(header, null);
		return new ResponseEntity<>(response, status);
	}

}
