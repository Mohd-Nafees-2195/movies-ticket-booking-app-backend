package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.ticketServices.Model.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

}
