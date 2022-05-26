package com.bookshop.dto.response;

import org.springframework.http.HttpStatus;

public class ResponseBuilder {

	private ResponseBuilder() {

	}

	public static <T> ServiceReponse<T> success(T data, HttpStatus status) {
		ServiceResponseHeader header = new ServiceResponseHeader(status, null);
		return new ServiceReponse<>(header, data);
	}

	public static <T> ServiceReponse<T> failure(String errorMessage, HttpStatus status) {
		ServiceResponseHeader header = new ServiceResponseHeader(status, errorMessage);
		return new ServiceReponse<>(header, null);
	}

}
