package com.bookshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookshop.domain.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
