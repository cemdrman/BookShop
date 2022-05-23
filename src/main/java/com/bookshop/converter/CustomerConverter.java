package com.bookshop.converter;

import org.springframework.stereotype.Service;

import com.bookshop.domain.Customer;
import com.bookshop.dto.request.CustomerRequset;
import com.bookshop.dto.response.CustomerResponse;

@Service
public class CustomerConverter {

	public CustomerResponse convert(Customer customer) {
		return CustomerResponse.builder().email(customer.getEmail()).name(customer.getName())
				.surname(customer.getSurname()).build();

	}

	public Customer convert(CustomerRequset request) {
		return Customer.builder().email(request.getEmail()).name(request.getName()).surname(request.getSurname())
				.password(request.getPassword()).build();

	}

}
