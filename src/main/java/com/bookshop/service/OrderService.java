package com.bookshop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bookshop.converter.OrderConverter;
import com.bookshop.domain.Customer;
import com.bookshop.domain.Order;
import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.exception.CustomerNotFoundException;
import com.bookshop.repository.CustomerRespository;
import com.bookshop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;
	private final OrderConverter converter;
	private final CustomerRespository customerRespository;

	@Value(value = "${paging.size}")
	private int defaultSize;

//	public PagingResponse<OrderResponse> getAll(int page, int size) {
//
//		size = size == 0 ? defaultSize : size;
//
//		Page<Order> orders = repository.findAll(PageRequest.of(page, size));
//
//		return new PagingResponse<>(new OrderResponse(orders));
//	}

	public OrderResponse create(OrderRequest request) {

		Customer customer = customerRespository.findById(request.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("customer not found"));

		Order order = converter.convert(request);
		order.setCustomer(customer);

		Order savedOrder = repository.save(order);

		return new OrderResponse(savedOrder);
	}

	public void getByCustomerId(Integer id) {
		// TODO Auto-generated method stub

	}

}
