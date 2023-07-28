package com.movies.ticketServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Timing;

@Repository
public interface TimingRepository extends JpaRepository<Timing, Integer> {

}
