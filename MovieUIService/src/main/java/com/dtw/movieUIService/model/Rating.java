package com.dtw.movieUIService.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Rating {

	private Long id;
	@NotNull
	@Positive
	private Long movieId;
	@NotNull
	@Min(0) @Max(10)
	private int rating;
}