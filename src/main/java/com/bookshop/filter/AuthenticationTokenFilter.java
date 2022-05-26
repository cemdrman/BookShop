package com.bookshop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.bookshop.exception.AuthenticationFailedException;
import com.bookshop.service.AuthenticationService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationTokenFilter extends GenericFilterBean {

	private final AuthenticationService authenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		try {
			Authentication userAuthentication = authenticationService.getUserAuthentication(httpServletRequest);
			SecurityContextHolder.getContext().setAuthentication(userAuthentication);

		} catch (AuthenticationFailedException e) {
			handleSecurityException((HttpServletResponse) response, "Authentication header is not valid");
			log.debug(e.getMessage());
		} catch (MalformedJwtException e) {
			handleSecurityException((HttpServletResponse) response, "Jwt was not correctly constructed");
			log.debug(e.getMessage());
		} catch (ExpiredJwtException e) {
			handleSecurityException((HttpServletResponse) response, "Token already expired");
			log.debug(e.getMessage());
		}
		chain.doFilter(request, response);

	}

	private void handleSecurityException(HttpServletResponse response, String message) throws IOException {
		SecurityContextHolder.clearContext();
		response.sendError(HttpStatus.UNAUTHORIZED.value(), message);
	}

}
