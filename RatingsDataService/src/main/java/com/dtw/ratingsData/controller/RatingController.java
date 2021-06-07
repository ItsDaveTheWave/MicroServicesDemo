package com.dtw.ratingsData.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtw.ratingsData.entity.Rating;
import com.dtw.ratingsData.repo.RatingRepo;

@RestController
@RequestMapping("/rating")
@CrossOrigin("http://localhost:4200")
public class RatingController {

	@Autowired
	private RatingRepo ratingRepo;
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getOne(@PathVariable Long ratingId) {
		
		Optional<Rating> ratingOpt = ratingRepo.findById(ratingId);
		if(ratingOpt.isPresent()) return ResponseEntity.ok(ratingOpt.get());
		return new ResponseEntity<Rating>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_admin')")
	public ResponseEntity<List<Rating>> getAll() {
		return ResponseEntity.ok(ratingRepo.findAll());
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('create_rating')")
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		return new ResponseEntity<Rating>(ratingRepo.save(rating), HttpStatus.CREATED);
	}
}