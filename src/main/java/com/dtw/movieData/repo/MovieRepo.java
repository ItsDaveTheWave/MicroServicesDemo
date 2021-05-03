package com.dtw.movieData.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtw.movieData.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long>{

}