package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.ticketServices.Model.Timing;

public interface TimingRepository extends JpaRepository<Timing, Integer> {

}
