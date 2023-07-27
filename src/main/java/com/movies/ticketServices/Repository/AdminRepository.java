package com.movies.ticketServices.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.ticketServices.Model.ApplicationAdmin;

public interface AdminRepository extends JpaRepository<ApplicationAdmin, Integer> {
	
  Optional<ApplicationAdmin> findByEmail(String email);
}
