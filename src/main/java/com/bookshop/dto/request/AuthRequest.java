package com.bookshop.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	
	@NotBlank(message = "email.notBlank")
	private String email;
	
	@NotBlank(message = "password.notBlank")
	private String password;

}
