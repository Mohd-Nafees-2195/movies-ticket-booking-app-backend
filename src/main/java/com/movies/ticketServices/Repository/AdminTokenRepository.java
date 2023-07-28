package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.AdminTokens;

@Repository
public interface AdminTokenRepository extends JpaRepository<AdminTokens, Integer> {

}
