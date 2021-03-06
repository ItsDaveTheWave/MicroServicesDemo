package com.dtw.movieData;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@EnableEurekaClient
@EnableResourceServer
@SpringBootApplication
public class MovieDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDataServiceApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(
						HttpClient.create().responseTimeout(Duration.ofMillis(10000))));
	}
}