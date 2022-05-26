package com.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookshop.converter.BookConverter;
import com.bookshop.domain.Book;
import com.bookshop.dto.request.UpdateBookRequest;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.dto.response.PaginationBookResponse;
import com.bookshop.exception.BookShopException;
import com.bookshop.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final BookConverter converter;

	@Value(value = "${paging.size}")
	private int defaultSize;

	public PaginationBookResponse getAll(int page, int size) {

		size = size == 0 ? defaultSize : size;

		Page<Book> books = bookRepository.findAll(PageRequest.of(page, size));

		List<BookResponse> bookResponse = converter.convert(books);

		//// @formatter:off
		 return  PaginationBookResponse.builder()
				 .books(bookResponse)
				 .totalElements(books.getTotalElements())
				 .totalPages(books.getTotalPages())
				 .last(books.isLast())
				 .build();
				 
		// @formatter:on

	}

	public BookResponse getById(Integer id) {

		Book book = bookRepository.findById(id).orElseThrow(() -> new BookShopException("book.not.found"));

		return converter.convert(book);
	}

	public BookResponse update(Integer id, UpdateBookRequest request) {

		Book book = bookRepository.findById(id).orElseThrow(() -> new BookShopException("book.not.found"));

		book.setQuantity(request.getQuantity());

		bookRepository.save(book);

		return converter.convert(book);
	}

}
