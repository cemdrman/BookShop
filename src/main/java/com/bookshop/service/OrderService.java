package com.bookshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.bookshop.dto.request.BookRequest;
import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PagingResponse;
import com.bookshop.exception.BookNotFoundException;
import com.bookshop.exception.BookShopException;
import com.bookshop.exception.CustomerNotFoundException;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CustomerRespository;
import com.bookshop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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

		Map<Integer, Integer> bookQuantityMap = prepareBookQuantityMap(request.getBooks());

		bookQuantityMap.keySet().forEach(this::decreaseQuantity);

		List<Book> orderedBooks = findOrderedBooks(request.getBooks());

		Order savedOrder = orderRepository.save(prepareOrder(customer, orderedBooks));

		//// @formatter:off
		return OrderResponse.builder()
				.books(bookConverter.convertToResponse(savedOrder.getBooks()))
				.customer(customerConverter.convert(savedOrder.getCustomer()))
				.build();
		// @formatter:on

	}

	private List<Book> findOrderedBooks(List<BookRequest> books) {
		List<Book> orderedBooks = new ArrayList<>();
		books.forEach(book -> {
			Book foundBook = bookRepository.findById(book.getId()).get();
			foundBook.setQuantity(book.getQuantity());
			orderedBooks.add(foundBook);
		});
		return orderedBooks;
	}

	private Order prepareOrder(Customer customer, List<Book> books) {
		//// @formatter:off
		return Order.builder()
				.books(books)
				.customer(customer)
				.status(OrderStatus.PENDING)
				.build();

		// @formatter:on

	}

	private void decreaseQuantity(Integer bookId) {
		Book foundBook = bookRepository.findById(bookId).get();
		foundBook.setQuantity(Math.subtractExact(foundBook.getQuantity(), defaultSize));
		bookRepository.save(foundBook);
	}

	private Map<Integer, Integer> prepareBookQuantityMap(List<BookRequest> bookRequests) {

		Map<Integer, Integer> bookQuantityMap = new HashMap<>();

		bookRequests.forEach(bookRequest -> {
			Book book = bookRepository.findById(bookRequest.getId())
					.orElseThrow(() -> new BookNotFoundException("book not found"));
			bookQuantityMap.put(book.getId(), book.getQuantity());
		});
		return bookQuantityMap;
	}

	public PagingResponse<OrderResponse> getAllByCustomerId(int page, int size, Integer id) {
		size = size == 0 ? defaultSize : size;
		Page<Order> orderList = orderRepository.findAllByCustomer_Id(PageRequest.of(page, size), id);

		return orderConverter.applyPage(orderList);

	}

	public OrderResponse getById(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new BookShopException("order.not.found"));
		return orderConverter.convert(order);
	}

}
