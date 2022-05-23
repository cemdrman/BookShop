package com.bookshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookshop.converter.BookConverter;
import com.bookshop.dto.response.BookResponse;
import com.bookshop.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository repository;
	private final BookConverter converter;

	public List<BookResponse> getAll() {

		return null;
	}
	
	public BookResponse getById(Integer id) {
		
		return null;
	}

}
