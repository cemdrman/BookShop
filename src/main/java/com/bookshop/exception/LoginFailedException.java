package com.bookshop.exception;

public class LoginFailedException extends BookShopException {

	private static final long serialVersionUID = -3406189191342716966L;

	public LoginFailedException(String errorMessage) {
		super(errorMessage);
	}

}
