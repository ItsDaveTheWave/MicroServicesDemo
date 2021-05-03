package com.dtw.ratingsData.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtw.ratingsData.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long> {

}
