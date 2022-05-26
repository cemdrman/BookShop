package com.bookshop.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {

	private Long id;

	private String name;

	private String surname;

	private String email;

}
