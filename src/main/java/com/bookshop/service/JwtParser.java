package com.bookshop.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtParser {

	@Value(value = "${jwt.token.secret}")
	private String secretKey;

	public Claims getClaims(String jwt) {

		//// @formatter:off
		return Jwts.parser()
				.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
				.parseClaimsJws(jwt)
				.getBody();
		// @formatter:on

	}

}
