package com.greenway.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.greenway.BackendSpringBootApplication;
import com.greenway.model.User;
import com.greenway.payload.request.LoginRequest;
import com.greenway.payload.request.ProfileRequest;
import com.greenway.payload.request.SignupRequest;
import com.greenway.payload.response.JwtResponse;
import com.greenway.payload.response.MessageResponse;
import com.greenway.payload.response.ProfileResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getBaseUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	public void contextLoads() {
		
	}
	
	
	private String accessToken;
	@Before
	public void registerAndLogin() {
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setEmail("test@gmail.com");
		signupRequest.setPassword("1234567");
		
		ResponseEntity<MessageResponse> signupResponse = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/auth/signup", signupRequest, MessageResponse.class);
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("test@gmail.com");
		loginRequest.setPassword("1234567");
		
		ResponseEntity<JwtResponse> postResponse = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/auth/signin", loginRequest, JwtResponse.class);
		
		assertNotNull(postResponse);
		
		assertNotNull(postResponse.getBody());
		
		assertEquals(loginRequest.getEmail(), postResponse.getBody().getEmail());
		
		this.accessToken = "Bearer " + postResponse.getBody().getAccessToken();
	}
	
	private HttpHeaders getHeaders() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));        
	    headers.add("User-Agent", "Spring's RestTemplate" );  // value can be whatever
	    headers.add("Authorization", this.accessToken );
	    
	    return headers;
	}
	
	
	@Test
	public void testProfileUpdate() {
		
		//Test profile editing

		// new input
	    ProfileRequest newProfile = new ProfileRequest();
	    newProfile.setEmail("test_b@test.com");
	    newProfile.setPhoneNum("1231231234");
	    newProfile.setNickName("JunitTest");
	    newProfile.setPublicInfo(true);
		
		HttpEntity<ProfileRequest> request = new HttpEntity<ProfileRequest>(newProfile,getHeaders());
		ResponseEntity<ProfileResponse> putResponse = testRestTemplate.exchange(this.getBaseUrl()+"/api/v1/profiles/1", HttpMethod.PUT, request, ProfileResponse.class);
		
		assertNotNull(putResponse.getBody());
		assertEquals(putResponse.getBody().getEmail(), newProfile.getEmail());
		assertEquals(putResponse.getBody().getNickName(), newProfile.getNickName());
		assertEquals(putResponse.getBody().getPhoneNumber(), newProfile.getPhoneNum());
		assertEquals(putResponse.getBody().getPublicInfo(), newProfile.getPublicInfo());

	}

}
