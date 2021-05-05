package com.dtw.movieCatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Movie {

	private Long id;
	private String title;
	private String sinopsis;
}