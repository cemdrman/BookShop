package com.bookshop.converter;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bookshop.domain.Order;
import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderConverter {

	private final BookConverter bookConverter;
	private final CustomerConverter customerConverter;

	public Order convert(OrderRequest request) {
		//// @formatter:off
 
		return Order.builder()
			 .books(bookConverter.convert(request.getBooks()))
			 .build();
										 
			 
		// @formatter:on

	}

	public PagingResponse<OrderResponse> applyPage(Page<Order> orderList) {

		//// @formatter:off
		return new PagingResponse<>(orderList.getContent().stream().map(this::convert).collect(Collectors.toList()),
				orderList.getTotalPages(),
				orderList.getTotalElements(),
				orderList.getPageable().getPageSize(),
				orderList.getPageable().getPageNumber(),
				orderList.isEmpty());
		// @formatter:on

	}

	public OrderResponse convert(Order order) {
		//// @formatter:off
		 
		return OrderResponse.builder()
				.books(bookConverter.convertToResponse(order.getBooks()))
				.customer(customerConverter.convert(order.getCustomer()))
				.build();
		// @formatter:on

	}

}
