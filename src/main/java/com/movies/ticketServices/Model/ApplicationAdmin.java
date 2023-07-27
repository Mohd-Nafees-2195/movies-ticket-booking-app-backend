package com.movies.ticketServices.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="admin_tbl")
public class ApplicationAdmin implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;

	private String username;

	@Column(unique=true)
	private String email;


	private String password;
	@Column(name="is_email_verified")
	private Boolean isEmailVerified;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="admin_role_juction",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
			)
	private Set<Role> authorities;

	@OneToOne
	@JoinColumn(name="token_id")
	private AdminTokens adminToken;
	
	
	
	public ApplicationAdmin() {
		super();
		this.authorities=new HashSet<>();
	}
	
	

	public ApplicationAdmin(Integer userId, String username, String email, String password, Boolean isEmailVerified,
			 AdminTokens adminToken,Set<Role> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isEmailVerified = isEmailVerified;
		this.authorities = authorities;
		this.adminToken = adminToken;
	}

	
	
	
	
	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}



	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}



	public AdminTokens getUserToken() {
		return adminToken;
	}



	public void setUserToken(AdminTokens adminToken) {
		this.adminToken = adminToken;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
