package com.dtw.movieCatalog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dtw.movieCatalog.model.CatalogItem;
import com.dtw.movieCatalog.model.Movie;
import com.dtw.movieCatalog.model.Rating;
import com.dtw.movieCatalog.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		
		UserRating userRating = webClientBuilder.build()
				.get()
				.uri("http://localhost:8083/ratings/users/" + userId)
				.retrieve()
				.bodyToMono(UserRating.class)
				.block();
		
		return userRating.getUserRating().stream().map(rating -> {
			Movie movie = webClientBuilder.build()
				.get()
				.uri("http://localhost:8082/movies/" + rating.getMovieId())
				.retrieve()
				.bodyToMono(Movie.class)
				.block();
			return new CatalogItem(movie.getName(), "Description", rating.getRating());
		}).collect(Collectors.toList());
	}
}