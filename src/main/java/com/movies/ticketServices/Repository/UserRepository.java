package com.movies.ticketServices.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

	Optional<ApplicationUser> findByUsername(String email);
	Optional<ApplicationUser> findByEmail(String email);
}
