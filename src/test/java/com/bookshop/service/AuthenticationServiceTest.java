package com.bookshop.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookshop.converter.IdentityUserConverter;
import com.bookshop.domain.Customer;
import com.bookshop.dto.request.AuthRequest;
import com.bookshop.dto.response.AuthResponse;

@SpringBootTest
class AuthenticationServiceTest {

	@InjectMocks
	private AuthenticationService authenticationService;

	@Mock
	private CustomerService customerService;

	@Mock
	private TokenService tokenService;

	@Mock
	private IdentityUserConverter identityUserConverter;

	@Test
	void createTokenTest() {

		AuthRequest authRequest = prepareAuthRequest();

		Customer customer = prepareCustomer();

		String token = "token";

		Mockito.when(customerService.login(authRequest)).thenReturn(customer);

		Mockito.when(tokenService.createToken(customer)).thenReturn(token);

		AuthResponse authResponse = authenticationService.createToken(authRequest);

		assertThat(authResponse).isNotNull();
		assertThat(authResponse.getJwtToken()).isEqualTo(token);

	}

	private Customer prepareCustomer() {
		return Customer.builder().email("cem@gmail.com").id(1).isActive(true).build();
	}

	private AuthRequest prepareAuthRequest() {
		return AuthRequest.builder().email("cem@gmail.com").password("password").build();
	}

}
