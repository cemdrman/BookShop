package com.bookshop.exception;

public class TokenCreationException extends BookShopException {

	public TokenCreationException(String errorMessage) {
		super("Token could not created," + errorMessage);
		// TODO Auto-generated constructor stub
	}

}
