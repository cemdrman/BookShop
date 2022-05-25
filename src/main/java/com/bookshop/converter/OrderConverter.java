package com.bookshop.converter;

import org.springframework.stereotype.Service;

import com.bookshop.domain.Order;
import com.bookshop.dto.request.OrderRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderConverter {

	private final BookConverter bookConverter;

	public Order convert(OrderRequest request) {
		//// @formatter:off
 
		return Order.builder()
			 .books(bookConverter.convert(request.getBooks()))
			 .build();
										 
			 
		// @formatter:on

	}

}
