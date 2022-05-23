package com.bookshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.request.OrderRequest;
import com.bookshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService service;
	
	@GetMapping
	public void getAll() {
		service.getAll();
		
	}
	
	@GetMapping(value = "/customers/{id}")
	public void getByCustomerId(@PathVariable Integer id) {
		service.getByCustomerId(id);
		
	}
	
	@PostMapping
	public void create(@RequestBody OrderRequest request) {
		service.create(request);
		
	}

}
