package com.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookshop.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
