package com.dtw.movieData.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtw.movieData.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		return new Movie(movieId, "Test name");
	}
}