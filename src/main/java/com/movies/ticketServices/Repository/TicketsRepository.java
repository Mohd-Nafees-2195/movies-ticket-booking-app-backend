package com.movies.ticketServices.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {
	@Query(value="SELECT * FROM ticket_tbl t WHERE t.user_id=:userId ORDER BY t.date DESC",nativeQuery = true)
	List<Tickets> findAllByUserId(Integer userId);

}
