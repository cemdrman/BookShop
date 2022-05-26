package com.bookshop.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bookshop.configuration.JwtSecurityTokenConfig;
import com.bookshop.domain.Customer;
import com.bookshop.exception.AuthenticationFailedException;
import com.bookshop.util.DateUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private static final String BEARER = "Bearer";
	private static final String AUTHORIZATION = "Authorization";
	private static final int TOKEN_EXPIRATION_HOURS_COUNT = 3;

	private final JwtParser jwtParser;
	private final JwtSecurityTokenConfig tokenConfig;

	public String createToken(Customer customer) {
		Map<String, Object> tokenData = new HashMap<>();
		tokenData.put("id", customer.getId());
		tokenData.put("email", customer.getEmail());

		LocalDateTime now = LocalDateTime.now();

		//// @formatter:off

		return Jwts.builder()
				.addClaims(tokenData)
				.setExpiration(DateUtil.converToDate(now.plusHours(TOKEN_EXPIRATION_HOURS_COUNT)))
				.setIssuedAt(DateUtil.converToDate(now))
				.compressWith(CompressionCodecs.GZIP)
				.signWith(SignatureAlgorithm.HS512, tokenConfig.getSecretKeySpec())
				.compact();
		// @formatter:on

	}

	public Claims getTokenClaims(HttpServletRequest servletRequest) {
		return jwtParser.getClaims(getTokenFromHeader(servletRequest));
	}

	private String getTokenFromHeader(HttpServletRequest servletRequest) {
		String authenticationHeader = servletRequest.getHeader(AUTHORIZATION);
		boolean isStartWithBearer = StringUtils.startsWith(authenticationHeader, BEARER);
		String[] headerParams = StringUtils.split(authenticationHeader, StringUtils.SPACE);
		boolean isAuthenticationHeaderValid = authenticationHeader != null && isStartWithBearer
				&& headerParams.length == 2;
		if (!isAuthenticationHeaderValid) {
			throw new AuthenticationFailedException("Authentication header is not valid");
		}

		return authenticationHeader.split(StringUtils.SPACE)[1];
	}

}
