package com.hashedin.foodapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.hashedin.foodapp.service.UsersService;

@EnableMethodSecurity(prePostEnabled=true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Environment environment;
	
	public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UsersService usersService, Environment environment) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.usersService = usersService;
		this.environment = environment;
	}
	
	@Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		
		// configure authentication manager builder
	 	AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
	 	authenticationManagerBuilder.userDetailsService(this.usersService).passwordEncoder(this.bCryptPasswordEncoder);
	 	AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
	 	
	 // create Authentication filter
	 	AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager,usersService, environment);
	 	authenticationFilter.setFilterProcessesUrl(this.environment.getProperty("login.url.path"));
		
			httpSecurity.csrf((csrf) -> csrf.disable());
			httpSecurity
			.authorizeHttpRequests((auth) -> auth.requestMatchers("/users/createusers", "/users/**",  "/actuator/**")
			.permitAll())
			.addFilter(new com.hashedin.foodapp.security.AuthorizationFilter(authenticationManager, environment))
			.addFilter(authenticationFilter)
			.authenticationManager(authenticationManager)
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			return httpSecurity.build();
		}

}
