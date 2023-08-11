package com.movies.ticketServices.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Movies;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

//	Optional<Movies> fineAllMovies();
	//List<Movies> findAllByOrderByIdAsc(PageRequest pageRequest);
	
	 @Query(value = "SELECT * FROM movies_tbl t WHERE t.theatre_id = :theatetId", nativeQuery = true)
	 List<Movies> findAllByTheaterId(Integer theatetId);
	 
	 @Query(value="SELECT * FROM movies_tbl t WHERE t.movie_name LIKE %:title%",nativeQuery = true)
	 List<Movies> searchByTitleLike(String title);
}
