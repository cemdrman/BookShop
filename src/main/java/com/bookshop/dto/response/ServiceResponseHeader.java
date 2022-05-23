package com.bookshop.dto.response;

import org.springframework.http.HttpStatus;

public class ServiceResponseHeader {

	private HttpStatus statusCode;
	private String errorMessage;

	public ServiceResponseHeader(HttpStatus status, String message) {
		this.statusCode = status;
		this.errorMessage = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
