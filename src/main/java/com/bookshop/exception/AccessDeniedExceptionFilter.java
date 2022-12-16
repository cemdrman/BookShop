package com.bookshop.exception;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

public class AccessDeniedExceptionFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc)
			throws ServletException, IOException {
		try {
			fc.doFilter(request, response);
		} catch (AccessDeniedException e) {
			// log error if needed here then redirect
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(ALREADY_FILTERED_SUFFIX);
			requestDispatcher.forward(request, response);

		}

	}
}
