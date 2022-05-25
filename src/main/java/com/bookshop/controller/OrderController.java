package com.bookshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.request.OrderRequest;
import com.bookshop.dto.response.OrderResponse;
import com.bookshop.dto.response.PagingResponse;
import com.bookshop.dto.response.ResponseBuilder;
import com.bookshop.dto.response.ServiceReponse;
import com.bookshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService service;
//
//	@GetMapping
//	public ResponseEntity<ServiceReponse<PagingResponse<OrderResponse>>> getAll(int page, int size) {
//		return ResponseBuilder.success(service.getAll(page, size), HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/customers/{id}")
//	public ResponseEntity<ServiceReponse<OrderResponse>> getByCustomerId(@PathVariable Integer id) {
//		return ResponseBuilder.success(service.getByCustomerId(id), HttpStatus.OK);
//	}

	@PostMapping
	public ResponseEntity<ServiceReponse<OrderResponse>> create(@RequestBody OrderRequest request) {
		return ResponseBuilder.success(service.create(request), HttpStatus.CREATED);
	}

}
