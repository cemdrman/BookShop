package com.bookshop.domain;

import java.math.BigDecimal;

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
public class Book {
	
	private Integer id;
	
	private String name;
	
	private BigDecimal price;

	private Author author;
}
