package com.bookshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.service.StatisticsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/statistics")
@RequiredArgsConstructor
public class StatisticsController {

	private final StatisticsService service;

	@GetMapping
	public void getStatistics() {
		service.getAll();
	}

}
