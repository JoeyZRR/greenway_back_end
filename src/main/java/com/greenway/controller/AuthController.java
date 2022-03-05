package com.greenway.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenway.model.User;
import com.greenway.payload.request.LoginRequest;
import com.greenway.payload.request.SignupRequest;
import com.greenway.payload.response.JwtResponse;
import com.greenway.payload.response.MessageResponse;
import com.greenway.repository.UserRepository;
import com.greenway.security.jwt.JwtUtils;
import com.greenway.security.services.UserDetailsImpl;

/**
 * Rest controller for the user login and user registration
 * @author anniekhand
 *
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * Endpoint for user login
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		

		JwtResponse jwtResponse = new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getEmail());
		jwtResponse.setNickName(userDetails.getNickName());
		jwtResponse.setImageUrl(userDetails.getImageUrl());
		jwtResponse.setPhoneNumber(userDetails.getPhoneNumber());
		jwtResponse.setZipCode(userDetails.getZipCode());
		jwtResponse.setPublicProfile(userDetails.isPublicProfile());
		jwtResponse.setImageUrl(userDetails.getImageUrl());
		
		return ResponseEntity.ok(jwtResponse);
	}

	/**
	 * Endpoint for user registration
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		//setting first part of the email to be the nickname
		user.setNickName(user.getEmail().substring(0,user.getEmail().indexOf("@")));
		//set the default avatar image
		user.setImageUrl("https://greenway-avatars.s3.amazonaws.com/1627589051383-defaultAvatar.jpeg");
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
