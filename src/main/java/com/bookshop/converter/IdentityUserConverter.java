package com.bookshop.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.bookshop.dto.CustomerDto;
import com.bookshop.dto.IdentityUser;

import io.jsonwebtoken.Claims;
import lombok.val;

@Component
public class IdentityUserConverter {

	public IdentityUser convert(Claims claims) {

		//// @formatter:off
  
		CustomerDto customer= CustomerDto.builder()
				.id(getLongValue(claims,"id"))
				.name(getStringValue(claims, "name"))
				.surname(getStringValue(claims, "surname"))
				.email(getStringValue(claims, "email"))
				.build();
		// @formatter:on
		return IdentityUser.builder().customer(customer).build();
	}

	private String getStringValue(Claims claims, String key) {
		Object value = claims.getOrDefault(key, StringUtils.EMPTY);
		return value != null ? value.toString() : null;
	}

	private Long getLongValue(Claims claims, String key) {
		val value = claims.getOrDefault(key, StringUtils.EMPTY);
		return value != null ? Long.parseLong(value.toString()) : 0;
	}

}
