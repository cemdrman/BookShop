package com.bookshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bookshop.domain.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	Page<Order> findAllByCustomer_Id(PageRequest pageRequest, Integer id);

}
