package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Theatres;

@Repository
public interface TheatresRepository extends JpaRepository<Theatres, Integer> {
  
}
