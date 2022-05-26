package com.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.request.CustomerRequset;
import com.bookshop.dto.response.CustomerResponse;
import com.bookshop.dto.response.ResponseBuilder;
import com.bookshop.dto.response.ServiceReponse;
import com.bookshop.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<List<CustomerResponse>> getUser() {
		return ResponseBuilder.success(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<CustomerResponse> getUserById(@PathVariable(value = "id") Long userId) {
		return ResponseBuilder.success(customerService.getById(userId), HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<CustomerResponse> createUser(@RequestBody CustomerRequset customerRequest) {
		return ResponseBuilder.success(customerService.create(customerRequest), HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceReponse<CustomerResponse> updateUser(@RequestBody CustomerRequset userRequest) {
		return ResponseBuilder.success(customerService.update(userRequest), HttpStatus.OK);
	}

}
