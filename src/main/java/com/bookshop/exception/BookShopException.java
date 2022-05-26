package com.bookshop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookShopException extends RuntimeException {

	private static final long serialVersionUID = 3359153536957999615L;

	private String errorMessage;

}
