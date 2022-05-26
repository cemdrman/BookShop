package com.bookshop.service;

import org.springframework.stereotype.Service;

import com.bookshop.converter.CustomerConverter;
import com.bookshop.domain.Customer;
import com.bookshop.dto.request.AuthRequest;
import com.bookshop.dto.request.CustomerRequset;
import com.bookshop.dto.response.CustomerResponse;
import com.bookshop.exception.BookShopException;
import com.bookshop.exception.LoginFailedException;
import com.bookshop.repository.CustomerRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRespository customerRespository;
	private final CustomerConverter converter;

	public CustomerResponse getById(Integer id) {
		Customer customer = customerRespository.findById(id)
				.orElseThrow(() -> new BookShopException("customer.not.found"));

		return converter.convert(customer);
	}

	public CustomerResponse create(CustomerRequset customerRequest) {
		Customer customer = customerRespository.save(converter.convert(customerRequest));
		return converter.convert(customer);
	}

	public Customer login(AuthRequest request) {
		Customer customer = customerRespository.findByEmail(request.getEmail())
				.orElseThrow(() -> new BookShopException("customer.not.found"));

		if (!customer.getPassword().equals(request.getPassword())) {
			throw new LoginFailedException("email or password are wrong");
		}
		return customer;
	}

}
