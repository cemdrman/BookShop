package com.bookshop.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.request.AuthRequest;
import com.bookshop.dto.response.AuthResponse;
import com.bookshop.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authService;

	@PostMapping
	public AuthResponse createToken(@RequestBody @Valid AuthRequest request) {
		return authService.createToken(request);
	}

}
