package com.dtw.movieCatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dtw.movieCatalog.model.CatalogItem;
import com.dtw.movieCatalog.model.Movie;
import com.dtw.movieCatalog.model.Rating;

@Service
public class MovieCatalogService {

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	public List<Rating> getRatings(String token) {
		return webClientBuilder.build()
		.get()
		.uri("http://ratings-data-service/rating")
		.header("Authorization", token)
		.retrieve()
		.bodyToMono(new ParameterizedTypeReference<List<Rating>>() {})
		.block();		
	}
	
	public CatalogItem getCatalogItem(Rating rating, String token) {
		Movie movie = webClientBuilder.build()
				.get()
				.uri("http://movie-data-service/movie/" + rating.getMovieId())
				.header("Authorization", token)
				.retrieve()
				.bodyToMono(Movie.class)
				.block();
			return new CatalogItem(movie.getTitle(), movie.getSinopsis(), rating.getRating());
	}
}