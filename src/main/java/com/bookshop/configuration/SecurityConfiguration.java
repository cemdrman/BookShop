package com.bookshop.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookshop.filter.AuthenticationTokenFilter;
import com.bookshop.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final AuthenticationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//// @formatter:off
		http.cors().and()
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(new AuthenticationTokenFilter(authenticationService), UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests().anyRequest().authenticated();
		// @formatter:on

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		List<String> ignore = Arrays.asList("/", "/auth");
		web.ignoring().antMatchers(ignore.toArray(new String[] {}));
		web.ignoring().antMatchers(HttpMethod.POST, "/customers");
	}

}
