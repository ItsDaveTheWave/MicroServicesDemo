package com.dtw.movieCatalog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtw.movieCatalog.model.CatalogItem;
import com.dtw.movieCatalog.model.Rating;
import com.dtw.movieCatalog.service.MovieCatalogService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieCatalogService catalogService;
	
	@GetMapping
	public List<CatalogItem> getCatalog() {
		
		List<Rating> ratingList = catalogService.getRatings();
		
		return ratingList.stream().map(rating -> catalogService.getCatalogItem(rating)).collect(Collectors.toList());
	}
}