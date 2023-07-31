package com.movies.ticketServices.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.movies.ticketServices.JwtUtils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
public class UserConfiguration {


	private final RSAKeyProperties keys;

	public UserConfiguration(RSAKeyProperties keys) {
		this.keys=keys;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationManager authManager(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoProvider=new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(userDetailsService);
		daoProvider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(daoProvider);
	}




	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	 http.csrf(csrf->csrf.disable())
    	        .cors(Customizer.withDefaults())
    			.authorizeHttpRequests(auth -> {
    				auth.requestMatchers("/auth/**").permitAll();
    				auth.requestMatchers("/admin/**").hasRole("ADMIN");
    				auth.requestMatchers("/user/**").hasAnyRole("ADMIN","USER");
    				//auth.anyRequest().authenticated();
    			});

    	  http.oauth2ResourceServer()
    			   .jwt()
    			   .jwtAuthenticationConverter(jwtAuthenticationConverter());

    	  http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    	  return http.build();
    }
	
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOrigins(Arrays.asList("*"));
//	    configuration.setAllowedMethods(Arrays.asList("*"));
//	    configuration.setAllowedHeaders(Arrays.asList("*"));
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
	
//	@Bean
//	public FilterRegistrationBean<CorsFilter> coresFilter() {
//		
//		
//		CorsConfiguration corsConfiguration=new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.addAllowedOriginPattern("*");
//		corsConfiguration.addAllowedHeader("Authorization");
//		corsConfiguration.addAllowedHeader("Content-Type");
//		corsConfiguration.addAllowedHeader("Accept");
//		corsConfiguration.addAllowedHeader("POST");
//		corsConfiguration.addAllowedHeader("GET");
//		corsConfiguration.addAllowedHeader("DELETE");
//		corsConfiguration.addAllowedHeader("PUT");
//		corsConfiguration.addAllowedHeader("OPTIONS");
//		corsConfiguration.setMaxAge(3600L);
//		
//		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		//CorsFilter corsFilter = new CorsFilter(source);
//		//FilterRegistrationBean<CorsFilter> bean=new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
//		//return bean;
//	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		JWK jwk=new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
		JWKSource<SecurityContext> jwks=new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);

	}


	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		JwtAuthenticationConverter jwtConverter=new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtConverter;
	}
}
