package com.movies.ticketServices.Services;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

	private static final int TOKEN_LENGTH=16;

	@Autowired
	private JwtEncoder jwtEncoder;

	@Autowired
	private JwtDecoder jwtDecoder;

	public String generateJwt(Authentication auth) {
		 Instant now=Instant.now();
		 String scope=auth.getAuthorities().stream()
				 .map(GrantedAuthority::getAuthority)
				 .collect(Collectors.joining(" "));

		 JwtClaimsSet claims=JwtClaimsSet.builder()
				 .issuer("self")
				 .issuedAt(now)
				 .subject(auth.getName())
				 .claim("roles", scope)
				 .build();

		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public String generatePasswordResetToken() {
		SecureRandom secureRandom=new SecureRandom();
		byte[] randomeBytes=new byte[TOKEN_LENGTH];
		secureRandom.nextBytes(randomeBytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(randomeBytes);
	}

	 public String generateToken() {
	        return UUID.randomUUID().toString();
	    }

}
