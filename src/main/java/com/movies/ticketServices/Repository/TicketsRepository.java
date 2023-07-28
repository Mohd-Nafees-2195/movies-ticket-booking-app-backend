package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

}
