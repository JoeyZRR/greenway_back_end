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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.greenway.BackendSpringBootApplication;
import com.greenway.model.User;
import com.greenway.payload.request.LoginRequest;
import com.greenway.payload.request.SignupRequest;
import com.greenway.payload.response.JwtResponse;
import com.greenway.payload.response.MessageResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getBaseUrl() {
		return "http://localhost:" + port;
	}
	
	private String accessToken;
	
	@Test
	public void contextLoads() {
		
	}
	
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
	public void testAllAccess() {
	    
	    ResponseEntity<String> result = testRestTemplate.exchange(this.getBaseUrl() +"/api/v1/test/all", HttpMethod.GET, new HttpEntity<>("parameters", getHeaders()), String.class);
	    
	    assertEquals("Public Content.", result.getBody());
		
	}
	
	@Test
	public void testGetUsers() {
	    
	    ResponseEntity<String> result = testRestTemplate.exchange(this.getBaseUrl() +"/api/v1/test/users", HttpMethod.GET, new HttpEntity<>("parameters", getHeaders()), String.class);
	    
	    assertNotNull(result.getBody());
	    
		
	}

}
