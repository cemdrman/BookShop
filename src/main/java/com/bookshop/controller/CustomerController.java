package com.bookshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.request.CustomerRequset;
import com.bookshop.dto.response.CustomerResponse;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PagingResponse;
import com.bookshop.dto.response.ResponseBuilder;
import com.bookshop.dto.response.ServiceReponse;
import com.bookshop.service.CustomerService;
import com.bookshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;
	private final OrderService orderService;

	@GetMapping(value = "/{id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<PagingResponse<OrderResponse>> getUser(@PathVariable Integer id,
			@RequestParam(defaultValue = "0", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "size") int size) {
		return ResponseBuilder.success(orderService.getAllByCustomerId(page, size, id), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<CustomerResponse> getUserById(@PathVariable(value = "id") Integer userId) {
		return ResponseBuilder.success(customerService.getById(userId), HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<CustomerResponse> createUser(@RequestBody CustomerRequset customerRequest) {
		return ResponseBuilder.success(customerService.create(customerRequest), HttpStatus.CREATED);
	}

}
