package com.movies.ticketServices.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movies.ticketServices.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 System.out.println("In the user details services");

		 return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User is not Valid"));

//		 if(!username.equals("Ethen"))
//			 throw new UsernameNotFoundException("Not Ethen");
//
//		 Set<Role> roles=new HashSet<>();
//		 roles.add(new Role(1,"USER"));
//
//		return new ApplicationUser(1,"Ethen",passwordEncoder.encode("Abcd@1234"),roles);
	}

}
