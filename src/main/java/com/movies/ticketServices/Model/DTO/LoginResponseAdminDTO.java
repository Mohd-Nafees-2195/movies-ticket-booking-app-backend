package com.movies.ticketServices.Model.DTO;

import com.movies.ticketServices.Model.ApplicationAdmin;

public class LoginResponseAdminDTO {
	
	private ApplicationAdmin admin;
	private String token;
	
	
	
	public LoginResponseAdminDTO() {
		super();
	}



	public LoginResponseAdminDTO(ApplicationAdmin admin, String token) {
		super();
		this.admin = admin;
		this.token = token;
	}



	public ApplicationAdmin getAdmin() {
		return admin;
	}



	public void setAdmin(ApplicationAdmin admin) {
		this.admin = admin;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}

	
	
}
