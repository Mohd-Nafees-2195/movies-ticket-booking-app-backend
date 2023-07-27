package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.ticketServices.Model.AdminTokens;

public interface AdminTokenRepository extends JpaRepository<AdminTokens, Integer> {

}
