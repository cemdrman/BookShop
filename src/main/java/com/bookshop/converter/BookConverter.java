package com.bookshop.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bookshop.domain.Book;
import com.bookshop.dto.response.AuthorResponse;
import com.bookshop.dto.response.BookResponse;

@Service
public class BookConverter {

	public List<BookResponse> convert(Page<Book> books) {
		List<BookResponse> bookList = new ArrayList<>();

		books.forEach(book -> bookList.add(convert(book)));

		return bookList;
	}

	private BookResponse convert(Book book) {
		//// @formatter:off
 
		return BookResponse.builder()
				.author(AuthorResponse.builder()
						.email(book.getAuthor().getEmail())
						.name(book.getAuthor().getName())
						.build())
				.name(book.getName())
				.price(book.getPrice())
				.stock(book.getStock())
				.build();
		// @formatter:on

	}

}
