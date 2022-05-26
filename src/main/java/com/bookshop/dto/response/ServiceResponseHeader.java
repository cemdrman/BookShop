package com.bookshop.dto.response;

import org.springframework.http.HttpStatus;

public class ServiceResponseHeader {

	private HttpStatus statusCode;
	private String message;

	public ServiceResponseHeader(HttpStatus status, String message) {
		this.statusCode = status;
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}

}
