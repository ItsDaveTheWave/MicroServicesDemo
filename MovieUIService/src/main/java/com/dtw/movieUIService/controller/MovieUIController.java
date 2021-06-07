package com.dtw.movieUIService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.dtw.movieUIService.model.Rating;
import com.dtw.movieUIService.utils.DTWUtils;

@Controller
@EnableOAuth2Sso
public class MovieUIController extends WebSecurityConfigurerAdapter {

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated();
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/secure")
	public String secure() {
		return "secure";
	}
	
	@RequestMapping("/ratings")
	public String ratings(Model model) {
				
		try {
			ResponseEntity<List<Rating>> response = webClientBuilder.build()
					.get()
					.uri("localhost:8083/rating")
					.header("Authorization", DTWUtils.getAccessToken())
					.retrieve()
					.toEntity(new ParameterizedTypeReference<List<Rating>>() {})
					.block();
			
			model.addAttribute("ratings", response.getBody());
		} catch (WebClientResponseException e) {
			ResponseEntity<String> responseError = ResponseEntity.status(e.getRawStatusCode()).
					headers(e.getHeaders()).body(e.getResponseBodyAsString());
			model.addAttribute("error", responseError);
		}
		
		return "secure";
	}
}