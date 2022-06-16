package com.bookshop.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.bookshop.converter.BookConverter;
import com.bookshop.domain.Book;
import com.bookshop.dto.response.PaginationBookResponse;
import com.bookshop.repository.BookRepository;

@SpringBootTest
class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private BookConverter converter;

	@Value(value = "${paging.size}")
	private int defaultSize;

	@Test
	void getAllTest() {

		Page<Book> pageableBooks = preparePageableBooks();
		Mockito.when(bookRepository.findAll(PageRequest.of(0, 5))).thenReturn(pageableBooks);
		Mockito.when(bookRepository.findAll(PageRequest.of(0, 5))).thenReturn(pageableBooks);

		PaginationBookResponse bookResponse = bookService.getAll(0, 5);

		assertThat(bookResponse).isNotNull();
		assertThat(bookResponse.getBooks()).isNotEmpty();
		assertThat(bookResponse.getTotalElements()).isEqualTo(pageableBooks.getTotalElements());

	}

	private Page<Book> preparePageableBooks() {

		List<Book> books = new ArrayList<>();

		books.add(Book.builder().id(1).name("book1").build());
		books.add(Book.builder().id(2).name("book2").build());
		books.add(Book.builder().id(3).name("book3").build());
		return new PageImpl<Book>(books);
	}

}
