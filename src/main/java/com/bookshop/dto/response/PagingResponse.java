package com.bookshop.dto.response;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingResponse<T> {

	private Page<T> content;
	private Integer totalPage;
	private long totalElement;
	private Integer size;
	private Integer page;
	private boolean empty;

	public PagingResponse(Page<T> content) {
		super();
		this.content = content;
		this.totalElement = content.getTotalElements();
		this.totalPage = content.getTotalPages();
		this.size = content.getSize();
		this.page = content.getPageable().getPageNumber();
		this.empty = content.isLast();

	}

}
