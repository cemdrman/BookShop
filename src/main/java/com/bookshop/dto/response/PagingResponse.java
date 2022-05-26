package com.bookshop.dto.response;

import java.util.Collection;

import lombok.Data;

@Data
public class PagingResponse<T> {

	private Collection<T> content;
	private Integer totalPage;
	private long totalElement;
	private Integer size;
	private Integer page;
	private boolean empty;

	public PagingResponse(Collection<T> content, Integer totalPages, long totalElement, Integer size, Integer page,
			boolean empty) {
		super();
		this.content = content;
		this.totalPage = totalPages;
		this.totalElement = totalElement;
		this.size = size;
		this.page = page;
		this.empty = empty;

	}

}
