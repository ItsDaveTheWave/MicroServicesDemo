package com.dtw.movieCatalog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dtw.movieCatalog.model.CatalogItem;
import com.dtw.movieCatalog.model.Movie;
import com.dtw.movieCatalog.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping
	public List<CatalogItem> getCatalog() {
		
		List<Rating> ratingList = webClientBuilder.build()
				.get()
				.uri("http://ratings-data-service/rating")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Rating>>() {})
				.block();
		
		return ratingList.stream().map(rating -> {
			Movie movie = webClientBuilder.build()
				.get()
				.uri("http://movie-data-service/movie/" + rating.getMovieId())
				.retrieve()
				.bodyToMono(Movie.class)
				.block();
			return new CatalogItem(movie.getName(), rating.getRating());
		}).collect(Collectors.toList());
	}
}