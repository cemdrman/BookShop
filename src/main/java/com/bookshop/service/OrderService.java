package com.bookshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookshop.converter.BookConverter;
import com.bookshop.converter.CustomerConverter;
import com.bookshop.converter.OrderConverter;
import com.bookshop.domain.Book;
import com.bookshop.domain.Customer;
import com.bookshop.domain.Order;
import com.bookshop.domain.enums.OrderStatus;
import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PagingResponse;
import com.bookshop.exception.BookNotFoundException;
import com.bookshop.exception.CustomerNotFoundException;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CustomerRespository;
import com.bookshop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderConverter orderConverter;
	private final BookConverter bookConverter;
	private final CustomerConverter customerConverter;
	private final CustomerRespository customerRespository;
	private final BookRepository bookRepository;

	@Value(value = "${paging.size}")
	private int defaultSize;

	public PagingResponse<OrderResponse> getAll(int page, int size) {

		size = size == 0 ? defaultSize : size;

		Page<Order> orders = orderRepository.findAll(PageRequest.of(page, size));

		return orderConverter.applyPage(orders);
	}

	@Transactional
	public synchronized OrderResponse create(OrderRequest request) {

		Customer customer = customerRespository.findById(request.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("customer not found"));

		List<Book> orderBooks = new ArrayList<>();

		request.getBooks().forEach(bookRequest -> {
			Book book = bookRepository.findById(bookRequest.getId())
					.orElseThrow(() -> new BookNotFoundException("book not found"));
			orderBooks.add(book);
		});

		Order order = Order.builder().books(orderBooks).customer(customer).status(OrderStatus.COMPLETED).build();

		// istenilen quantity kadar var mı? stok kontrol
		// quantitiy düşür, update et.
		// istenen quantity değerini dön

		Order savedOrder = orderRepository.save(order);

		//// @formatter:off
		return OrderResponse.builder()
				.books(bookConverter.convertToResponse(savedOrder.getBooks()))
				.customer(customerConverter.convert(savedOrder.getCustomer()))
				.build();
		// @formatter:on

	}

	public PagingResponse<OrderResponse> getAllByCustomerId(int page, int size, Integer id) {
		size = size == 0 ? defaultSize : size;
		Page<Order> orderList = orderRepository.findAllByCustomer_Id(PageRequest.of(page, size), id);

		return orderConverter.applyPage(orderList);

	}

}
