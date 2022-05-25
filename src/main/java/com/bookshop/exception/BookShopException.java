package com.bookshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookShopException extends RuntimeException {
	
	private String errorMessage;

}
