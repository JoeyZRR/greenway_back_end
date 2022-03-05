package com.greenway.payload.request;

public class ProfileRequest {
	
	    private String email;
		
	    private String zipCode;
	   
	    private String phoneNum;
	    
	    private String nickName;
	    
	    private Boolean publicInfo;
	    
	    private String imageUrl;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getPhoneNum() {
			return phoneNum;
		}

		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public Boolean getPublicInfo() {
			return publicInfo;
		}

		public void setPublicInfo(Boolean publicInfo) {
			this.publicInfo = publicInfo;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		
}
