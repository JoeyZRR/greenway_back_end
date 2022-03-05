package com.greenway.payload.response;

import java.io.Serializable;

public class JwtResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5653718789575503561L;

	private String token;
	
	private String type = "Bearer";
	
	private Long id;
	
	private String email;
	
	private String nickName;
	
	private String imageUrl;
	
	private String phoneNumber;
	
	private boolean publicProfile;
	
	private String zipCode;

	public JwtResponse(String accessToken, Long id, String email) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPublicProfile() {
		return publicProfile;
	}

	public void setPublicProfile(boolean publicProfile) {
		this.publicProfile = publicProfile;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
