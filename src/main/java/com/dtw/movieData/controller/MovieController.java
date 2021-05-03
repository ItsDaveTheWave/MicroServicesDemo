package com.dtw.movieData.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtw.movieData.entity.Movie;
import com.dtw.movieData.repo.MovieRepo;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieRepo movieRepo;
	
	@GetMapping("/{movieId}")
	public ResponseEntity<Movie> getOne(@PathVariable Long movieId) {
		
		Optional<Movie> movieOpt = movieRepo.findById(movieId);
		if(movieOpt.isPresent()) return ResponseEntity.ok(movieOpt.get());
		return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> getAll() {
		return ResponseEntity.ok(movieRepo.findAll());
	}
}