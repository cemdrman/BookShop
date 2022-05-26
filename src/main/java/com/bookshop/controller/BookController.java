package com.bookshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.PaginationBookResponse;
import com.bookshop.dto.response.ResponseBuilder;
import com.bookshop.dto.response.ServiceReponse;
import com.bookshop.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping
	public ServiceReponse<PaginationBookResponse> getAllBooks(@RequestParam(defaultValue = "0", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "size") int size) {
		return ResponseBuilder.success(bookService.getAll(page, size), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ServiceReponse<BookResponse> getAllId(@PathVariable Integer id) {
		return ResponseBuilder.success(bookService.getById(id), HttpStatus.OK);
	}

}
