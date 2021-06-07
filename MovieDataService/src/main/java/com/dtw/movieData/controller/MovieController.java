package com.dtw.movieData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dtw.movieData.model.Movie;
import com.dtw.movieData.model.TmdbPojo;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Value("${theMovieDB.api.key}")
	private String apiKey;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{movieId}")
	public ResponseEntity<Movie> getOne(@PathVariable Long movieId) {
		
		TmdbPojo movieDB = webClientBuilder.build()
				.get()
				.uri("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey)
				.retrieve()
				.bodyToMono(TmdbPojo.class)
				.block();			
		Movie movie = new Movie(movieDB.getId(), movieDB.getTitle(), movieDB.getOverview());
		return ResponseEntity.ok(movie);
	}
}