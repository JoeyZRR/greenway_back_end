package com.greenway.payload.response;

import java.io.Serializable;

public class ProfileResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -500047976055480267L;

	private Long userId;

	private String email;

	private String password;

    private String zipCode;
    
    private String phoneNumber;
	
    private String nickName;
    
    private String imageUrl;
    
    private Boolean publicInfo = true;
    
    public ProfileResponse() {
    	
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Boolean getPublicInfo() {
		return publicInfo;
	}

	public void setPublicInfo(Boolean publicInfo) {
		this.publicInfo = publicInfo;
	}
    
    
}
