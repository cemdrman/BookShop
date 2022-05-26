package com.bookshop.exception;

public class TokenCreationException extends BookShopException {

	private static final long serialVersionUID = -8222376254261798157L;

	public TokenCreationException(String errorMessage) {
		super("Token could not created, " + errorMessage);
	}

}
