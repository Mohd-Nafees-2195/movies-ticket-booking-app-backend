package com.movies.ticketServices.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movies.ticketServices.Model.UserTokens;

public interface TokensRepository extends JpaRepository<UserTokens, Integer> {
	
	Optional<UserTokens> findById(Integer id);
	
//	@Query("""
//			select * from token_tbl
//			""")
//     Optional<UserTokens> findAllTokens();
}
