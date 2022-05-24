package com.bookshop.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationBookResponse {

	private int totalPages;
	private long totalElements;
	private boolean last;

	private List<BookResponse> books;

}
