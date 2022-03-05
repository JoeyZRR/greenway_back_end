package com.greenway.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenway.model.User;
import com.greenway.payload.request.ProfileRequest;
import com.greenway.payload.response.ProfileResponse;
import com.greenway.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/profiles/")
	public class ProfileController{

	@Autowired
	UserRepository userRepository;
	
	@PutMapping("{id}")
	public ResponseEntity<?> saveUserProfile(@Valid @PathVariable("id") Long userId, @RequestBody ProfileRequest profileRequest)
	{	
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}else{
			
		User user = userOptional.get();
		if(profileRequest.getEmail() != null) {
			user.setEmail(profileRequest.getEmail());
		}
		if(profileRequest.getNickName() != null) {
			user.setNickName(profileRequest.getNickName());
		}
		if(profileRequest.getPhoneNum() != null) {
			user.setPhoneNumber(profileRequest.getPhoneNum());
		}
		if(profileRequest.getZipCode() != null) {
			user.setZipCode(profileRequest.getZipCode());
		}
		if(profileRequest.getPublicInfo() != null) {
			user.setPublicInfo(profileRequest.getPublicInfo());
		}
		if(profileRequest.getImageUrl() != null) {
			user.setImageUrl(profileRequest.getImageUrl());
		}
		
		userRepository.save(user);
		
		ProfileResponse response = new ProfileResponse();
		response.setUserId(user.getUserId());
		response.setEmail(user.getEmail());
		response.setImageUrl(user.getImageUrl());
		response.setNickName(user.getNickName());
		response.setPhoneNumber(user.getPhoneNumber());
		
		return ResponseEntity.ok(response);
		
	}
	
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getUserProfile(@Valid @PathVariable("id") Long userId)
	{	
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}else{
			
		User user = userOptional.get();
		
		return ResponseEntity.ok(user);
		
	}
	
	}
}
