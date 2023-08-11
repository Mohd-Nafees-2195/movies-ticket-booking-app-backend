package com.movies.ticketServices.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Timing;

@Repository
public interface TimingRepository extends JpaRepository<Timing, Integer> {

	 @Query(value = "SELECT * FROM time_tbl t WHERE t.movie_id = :movieId", nativeQuery = true)
	 List<Timing> findAllByMovieId(Integer movieId);
	 
	 @Modifying
	 @Query(value = "DELETE FROM time_tbl t WHERE t.movie_id = :movieId", nativeQuery = true)
	 void deleteByMovieId(Integer movieId);
}
