package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Movies;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

//	Optional<Movies> fineAllMovies();
}
