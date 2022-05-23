package com.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.response.BookResponse;
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
	public ResponseEntity<ServiceReponse<List<BookResponse>>> getAllBooks() {
		return ResponseBuilder.success(bookService.getAll(), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<ServiceReponse<BookResponse>> getAllId(@PathVariable Integer id) {
		return ResponseBuilder.success(bookService.getById(id), HttpStatus.OK);
	}

}
