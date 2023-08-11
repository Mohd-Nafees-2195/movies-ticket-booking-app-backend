package com.movies.ticketServices.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movies.ticketServices.Model.Theatres;

@Repository
public interface TheatresRepository extends JpaRepository<Theatres, Integer> {
	@Query(value="SELECT * FROM theatres_tbl t WHERE t.admin_id=:adminId",nativeQuery = true)
	List<Theatres> getAllTheaterById(Integer adminId);
}
