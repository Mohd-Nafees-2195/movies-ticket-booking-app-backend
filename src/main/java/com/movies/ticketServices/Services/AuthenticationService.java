package com.movies.ticketServices.Services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.ticketServices.Model.AdminTokens;
import com.movies.ticketServices.Model.ApplicationAdmin;
import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Role;
import com.movies.ticketServices.Model.UserTokens;
import com.movies.ticketServices.Model.DTO.LoginDTO;
import com.movies.ticketServices.Model.DTO.LoginResponseAdminDTO;
import com.movies.ticketServices.Model.DTO.LoginResponseDTO;
import com.movies.ticketServices.Model.DTO.RegistrationResponseDTO;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Repository.AdminRepository;
import com.movies.ticketServices.Repository.AdminTokenRepository;
import com.movies.ticketServices.Repository.RoleRepository;
import com.movies.ticketServices.Repository.TokensRepository;
import com.movies.ticketServices.Repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

	Random random=new Random(1000);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TokensRepository tokensRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminTokenRepository adminTokenRepository;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 //Admin Registration
	 public RegistrationResponseDTO registerAdmin(String username,String email,String password) {

			String encodedPassword=passwordEncoder.encode(password);
			Role userRole= roleRepository.findByAuthority("ADMIN").get();

			Set<Role> authorities=new HashSet<>();
			authorities.add(userRole);


			Optional<ApplicationAdmin> getUserDetails=adminRepository.findByEmail(email);
				if(getUserDetails.isEmpty()) {
					//System.out.println("Not Empty");
					//String emailVerificationToken=UUID.randomUUID().toString();
					AdminTokens tokens=adminTokenRepository.save(new AdminTokens(0,null,null,null,null,null,null));
					adminRepository.save(new ApplicationAdmin(0,username,email,encodedPassword,false,tokens,authorities));
					 return new RegistrationResponseDTO("Registration Success","NA");
				}else {
					return new RegistrationResponseDTO("Registration Failed","user Already Exit");
				}
		}
	 
	 

	//User Registration Service
 public RegistrationResponseDTO registerUser(String username,String email,String password) {

		String encodedPassword=passwordEncoder.encode(password);
		Role userRole= roleRepository.findByAuthority("USER").get();

		Set<Role> authorities=new HashSet<>();
		authorities.add(userRole);


		Optional<ApplicationUser> getUserDetails=userRepository.findByEmail(email);
			if(getUserDetails.isEmpty()) {
				//String emailVerificationToken=UUID.randomUUID().toString();
				UserTokens tokens=tokensRepository.save(new UserTokens(0,null,null,null,null,null,null));
				userRepository.save(new ApplicationUser(0,username,email,encodedPassword,false,tokens,authorities));
				 return new RegistrationResponseDTO("Registration Success","NA");
			}else {
				return new RegistrationResponseDTO("Registration Failed","user Already Exit");
			}
	}
 
//Admin Login Service
//public LoginResponseAdminDTO loginAdmin(LoginDTO body) {
//		try {
//
//			//String encodedPassword=passwordEncoder.encode(password);
//			System.out.println("Before Authentication");
//
//			ApplicationAdmin admin= adminRepository.findByEmail(body.getEmail()).get();
//
//			Authentication auth=authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(admin.getUsername(), body.getPassword())
//					);
//
//
//			String token=tokenService.generateJwt(auth);
//			System.out.println(token+" Here ");
//			admin.setIsEmailVerified(true);
//			adminRepository.save(admin);
//			saveAdminJWTTokens(token,admin);
//
//			return new LoginResponseAdminDTO(adminRepository.findByEmail(body.getEmail()).get(),token);
//
//		}catch(NoSuchElementException e) {
//			return new LoginResponseAdminDTO(new ApplicationAdmin(0,"Login Failed","Usr Not Found!! Please Register First","",false,null,null),"");
//		}catch(AuthenticationException e) {
//			return new LoginResponseAdminDTO(new ApplicationAdmin(0,"Login Failed","Incorrect Email or Password","",false,null,null),"");
//		}
//	}
// public void saveAdminJWTTokens(String token,ApplicationAdmin admin) {
//	 AdminTokens adminTokens=admin.getUserToken();
//	 adminTokens.setJWTToken(token);
//	 adminTokens.setExpiryTimeJWTToken(LocalDateTime.now().plusDays(1));
//	 adminTokenRepository.save(adminTokens);
// }


	//User Login Service

	public LoginResponseDTO loginUser(LoginDTO body) {
		try {

			//String encodedPassword=passwordEncoder.encode(password);
			System.out.println("Before Authentication");

			ApplicationUser user= userRepository.findByEmail(body.getEmail()).get();

			Authentication auth=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), body.getPassword())
					);


			
			String token=tokenService.generateJwt(auth);
			user.setIsEmailVerified(true);
			
			userRepository.save(user);
			
			saveJWTTokens(token,user);
			System.out.println("After Authentication");
			return new LoginResponseDTO(userRepository.findByEmail(body.getEmail()).get(),token);

		}catch(NoSuchElementException e) {
			return new LoginResponseDTO(new ApplicationUser(0,"Login Failed","Usr Not Found!! Please Register First","",false,null,null),"");
		}catch(AuthenticationException e) {
			return new LoginResponseDTO(new ApplicationUser(0,"Login Failed","Incorrect Email or Password","",false,null,null),"");
		}
	}

	public void saveJWTTokens(String token,ApplicationUser user) {

		UserTokens userToken=user.getUserTokens();
		userToken.setJWTToken(token);
		userToken.setExpiryTimeJWTToken(LocalDateTime.now().plusDays(1));
		tokensRepository.save(userToken);
	}

	//Password reset services

	//Request link for password reser
	public ResponceDTO requestPasswordResetOTP(String email) {

		try {
			ApplicationUser user= userRepository.findByEmail(email).get();
			if(user!=null) {
				SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
				simpleMailMessage.setTo(email);
				simpleMailMessage.setSubject("Password Reset Request");
				simpleMailMessage.setText("Click the link below to reset your password");

//				MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//				MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
//				mimeMessageHelper.setTo(email);
//				mimeMessageHelper.setSubject("Reset Password");
//				mimeMessageHelper.setText(
//
//						"""
//						  <div>
//						    <a href="https://localhost:8081/auth/requestpasswordresetotp?email=%s" target="_blank">Reset Password</a>
//
//						  </div>
//						""".formatted(email),true
//
//						);
//

				//String token=tokenService.generatePasswordResetToken();
				System.out.println("Email = "+email);
				//Generating OTP Of 4 Digit

				int otp=random.nextInt(999999);
				System.out.println("OTP = "+otp);
				javaMailSender.send(simpleMailMessage);
				//String token=tokenService.generateToken();
				UserTokens userToken=user.getUserTokens();
//				userToken.setPasswordResetToken(token);
//				userToken.setExpiryTimePRT(LocalDateTime.now().plusHours(1));
				//tokensRepository.save(userToken);
				//sendResetLink(email,token);
				return new ResponceDTO("Success!!","Reset link has been sent to your registered email");
			}else {

				return new ResponceDTO("Failed!!","Invalid User Email");
			}
		}catch(NoSuchElementException e) {
			return new ResponceDTO("Failed!!","Invalid User");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponceDTO("Failed!!","Something Went Wrong");
		}

	}

	public void sendResetLink(String email,String token) {
		String resetLink="http://localhost:8081/auth/resetpassword?token="+token;
		String subject = "Password Reset Request";
		String body = "Click the link below to reset your password:\n" + resetLink;

		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(body);

		System.out.println(email+" Before ");
		javaMailSender.send(message);

		System.out.println(email+" jsdnj djndin jwendjnd ");
	}

	public ResponceDTO logout(String email) {
		try {
			ApplicationUser user = userRepository.findByEmail(email).get();
		    if (user != null ) {
		        // Reset the password && user.getResetToken().equals(token) && user.getResetTokenExpiryTime().isAfter(LocalDateTime.now())
		        user.setIsEmailVerified(false);
		        userRepository.save(user);
		        return new ResponceDTO("Success!!","Logout Successfull");
		    } else {
		    	return new ResponceDTO("Failed!!","Invalid Email");
		    }
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}

	}

}




//
//public void resetPassword(String email, String token, String newPassword) {
//    User user = userRepository.findByEmail(email);
//    if (user != null && user.getResetToken().equals(token) && user.getResetTokenExpiryTime().isAfter(LocalDateTime.now())) {
//        // Reset the password
//        user.setPassword(newPassword);
//        user.setResetToken(null);
//        user.setResetTokenExpiryTime(null);
//        userRepository.save(user);
//    } else {
//        // Handle invalid token or expired token
//    }
//}
