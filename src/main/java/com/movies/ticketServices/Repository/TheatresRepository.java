package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.ticketServices.Model.Theatres;

public interface TheatresRepository extends JpaRepository<Theatres, Integer> {
  
}
