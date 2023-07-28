package com.movies.ticketServices.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.ApplicationAdmin;

@Repository
public interface AdminRepository extends JpaRepository<ApplicationAdmin, Integer> {
	
  Optional<ApplicationAdmin> findByUsername(String username);
  Optional<ApplicationAdmin> findByEmail(String email);
}
