//package com.hashedin.foodapp.orderservice.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@EnableMethodSecurity(prePostEnabled=true)
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//	
//	private Environment environment;
//	
//	public WebSecurityConfig(Environment environment) {
//		this.environment = environment;
//	}
//	
//	@Bean
//    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
//		
//		// configure authentication manager builder
//	 	AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//	 	AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//	 	
//	 // create Authentication filter
//		
//			httpSecurity.csrf((csrf) -> csrf.disable());
//			httpSecurity
//			.authorizeHttpRequests((auth) -> auth.requestMatchers("/cart/addtocart/{userId}/{foodId}", "/cart/addtocart/**")
//			.permitAll())
//			.addFilter(new AuthorizationFilter(authenticationManager, environment))
//			.authenticationManager(authenticationManager)
//			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//			return httpSecurity.build();
//		}
//
//}
