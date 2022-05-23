package com.bookshop.domain;

import java.util.ArrayList;
import java.util.List;

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
public class Author {
	
	private Integer id;

	private String name;
	
	private String email;
	
	private List<Book> books = new ArrayList<>(10);

}
