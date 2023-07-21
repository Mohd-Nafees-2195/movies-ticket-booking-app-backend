package com.movies.ticketServices.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tokens_tbl")
public class UserTokens {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="token_id")
	private Integer tokenId;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinTable(
//			name="user_token_junctions",
//			joinColumns = {@JoinColumn(name="token_id")},
//			inverseJoinColumns = {@JoinColumn(name="user_id")}
//			)
	
	@Column(name="jwt_token")
	private String JWTToken;
	@Column(name="jwt_token_expiry_time")
	private LocalDateTime ExpiryTimeJWTToken;
	
	@Column(name="password_reset_token")
	private String passwordResetToken;
	@Column(name="prt_expiry_time")
	private LocalDateTime ExpiryTimePRT;
	
	@Column(name="email_verification_token")
	private String emailVerificationToken;
	@Column(name="evt_expire_time")
	private LocalDateTime expiryTimeEVT;
	
	
	public UserTokens() {
		super();
	}



	public UserTokens(Integer tokenId, String jWTToken, LocalDateTime expiryTimeJWTToken, String passwordResetToken,
			LocalDateTime expiryTimePRT, String emailVerificationToken, LocalDateTime expiryTimeEVT) {
		super();
		this.tokenId = tokenId;
		JWTToken = jWTToken;
		ExpiryTimeJWTToken = expiryTimeJWTToken;
		this.passwordResetToken = passwordResetToken;
		ExpiryTimePRT = expiryTimePRT;
		this.emailVerificationToken = emailVerificationToken;
		this.expiryTimeEVT = expiryTimeEVT;
	}



	public Integer getTokenId() {
		return tokenId;
	}



	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}



	public String getJWTToken() {
		return JWTToken;
	}



	public void setJWTToken(String jWTToken) {
		JWTToken = jWTToken;
	}



	public LocalDateTime getExpiryTimeJWTToken() {
		return ExpiryTimeJWTToken;
	}



	public void setExpiryTimeJWTToken(LocalDateTime expiryTimeJWTToken) {
		ExpiryTimeJWTToken = expiryTimeJWTToken;
	}



	public String getPasswordResetToken() {
		return passwordResetToken;
	}



	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}



	public LocalDateTime getExpiryTimePRT() {
		return ExpiryTimePRT;
	}



	public void setExpiryTimePRT(LocalDateTime expiryTimePRT) {
		ExpiryTimePRT = expiryTimePRT;
	}



	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}



	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}



	public LocalDateTime getExpiryTimeEVT() {
		return expiryTimeEVT;
	}



	public void setExpiryTimeEVT(LocalDateTime expiryTimeEVT) {
		this.expiryTimeEVT = expiryTimeEVT;
	}
	
	
	
}
