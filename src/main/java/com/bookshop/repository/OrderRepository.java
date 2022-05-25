package com.bookshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookshop.domain.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}
