package com.bookshop.configuration;

import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.bookshop.exception.TokenCreationException;

import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtSecurityTokenConfig {

	@Value(value = "${jwt.token.secret}")
	private String secretKey;

	public SecretKeySpec getSecretKeySpec() {
		try {
			return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
		} catch (Exception e) {
			throw new TokenCreationException(e.getMessage());
		}
	}

}
