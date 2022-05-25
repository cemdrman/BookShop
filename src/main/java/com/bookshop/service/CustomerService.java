package com.bookshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookshop.converter.CustomerConverter;
import com.bookshop.domain.Customer;
import com.bookshop.dto.request.CustomerRequset;
import com.bookshop.dto.response.CustomerResponse;
import com.bookshop.exception.CustomerNotFoundException;
import com.bookshop.repository.CustomerRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRespository customerRespository;
	private final CustomerConverter converter;

	public List<CustomerResponse> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerResponse getById(Long id) {
		Customer customer = customerRespository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("customer not found"));

		return converter.convert(customer);
	}

	public CustomerResponse create(CustomerRequset customerRequest) {
		Customer customer = customerRespository.save(converter.convert(customerRequest));
		return converter.convert(customer);
	}

	public CustomerResponse update(CustomerRequset userRequest) {

		return null;
	}

}
