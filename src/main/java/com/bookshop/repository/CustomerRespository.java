package com.bookshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookshop.domain.Customer;

public interface CustomerRespository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

}
