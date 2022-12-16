package com.bookshop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.bookshop.configuration.HttpAuthenticationEntryPoint;
import com.bookshop.exception.AuthenticationFailedException;
import com.bookshop.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationTokenFilter extends GenericFilterBean {

	private final AuthenticationService authenticationService;
	private final HttpAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		try {
			Authentication userAuthentication = authenticationService.getUserAuthentication(httpServletRequest);
			SecurityContextHolder.getContext().setAuthentication(userAuthentication);
			SecurityContextHolder.getContext().setAuthentication(userAuthentication);
			chain.doFilter(request, response);
		} catch (AuthenticationFailedException e) {
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(httpServletRequest, httpServletResponse,
					new AuthenticationFailedException(e.getMessage()));
		}

	}

}
