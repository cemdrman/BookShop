package com.bookshop.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailedException extends AuthenticationException {

	private static final long serialVersionUID = -4058854154965616762L;

	public AuthenticationFailedException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

}
