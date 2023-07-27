package com.movies.ticketServices.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.ticketServices.Model.UserTokens;

public interface TokensRepository extends JpaRepository<UserTokens, Integer> {

	@Override
	Optional<UserTokens> findById(Integer id);

//	@Query("""
//			select * from token_tbl
//			""")
//     Optional<UserTokens> findAllTokens();
}
