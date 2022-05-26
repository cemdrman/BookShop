package com.bookshop.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bookshop.converter.IdentityUserConverter;
import com.bookshop.domain.Customer;
import com.bookshop.dto.CustomerAuthentication;
import com.bookshop.dto.request.AuthRequest;
import com.bookshop.dto.response.AuthResponse;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final CustomerService customerService;
	private final TokenService tokenService;
	private final IdentityUserConverter identityUserConverter;

	public AuthResponse createToken(AuthRequest request) {

		Customer customer = customerService.login(request);

		var token = getToken(customer);

		return AuthResponse.builder().jwtToken(token).build();
	}

	public Authentication getUserAuthentication(HttpServletRequest httpServletRequest) {
		final Claims claims = tokenService.getTokenClaims(httpServletRequest);
		return new CustomerAuthentication(identityUserConverter.convert(claims));
	}

	private String getToken(Customer customer) {
		return tokenService.createToken(customer);
	}

}
