package com.bookshop.dto.response;

import java.util.List;

import com.bookshop.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthorResponse {
	
	private String name;

	private String email;

	private List<Book> books;

}
