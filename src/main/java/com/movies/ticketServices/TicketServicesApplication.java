package com.movies.ticketServices;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.movies.ticketServices.Model.AdminTokens;
import com.movies.ticketServices.Model.ApplicationAdmin;
import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Role;
import com.movies.ticketServices.Model.UserTokens;
import com.movies.ticketServices.Repository.AdminRepository;
import com.movies.ticketServices.Repository.AdminTokenRepository;
import com.movies.ticketServices.Repository.RoleRepository;
import com.movies.ticketServices.Repository.TokensRepository;
import com.movies.ticketServices.Repository.UserRepository;

@SpringBootApplication
public class TicketServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketServicesApplication.class, args);
	}

	
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository,AdminRepository adminRepository,PasswordEncoder passwordEncoder,AdminTokenRepository tokensRepository) {
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent())
				return;
			Role adminRole=roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

		
			
			Set<Role> roles=new HashSet<>();
			roles.add(adminRole);
			AdminTokens token=tokensRepository.save(new AdminTokens(0,null,null,null,null,null,null));
			ApplicationAdmin admin=new ApplicationAdmin(1,"admin","admin@gmail.com",passwordEncoder.encode("Abcd@1234"),false,token,roles);
			adminRepository.save(admin);
		};
	}
}
