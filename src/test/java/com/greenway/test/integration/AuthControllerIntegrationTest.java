package com.greenway.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.greenway.BackendSpringBootApplication;
import com.greenway.payload.request.LoginRequest;
import com.greenway.payload.request.SignupRequest;
import com.greenway.payload.response.JwtResponse;
import com.greenway.payload.response.MessageResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerIntegrationTest {
	
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
	
	@Test
	public void testRegisterUser() {
		SignupRequest request = new SignupRequest();
		request.setEmail("junitTest@gmail.com");
		request.setPassword("1234567");
		
		ResponseEntity<MessageResponse> postResponse1 = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/auth/signup", request, MessageResponse.class);
		
		assertNotNull(postResponse1);
		
		assertNotNull(postResponse1.getBody());
		
		assertEquals("User registered successfully!", postResponse1.getBody().getMessage());
		

		request.setEmail("junitTest@gmail.com");
		request.setPassword("1234567");
		ResponseEntity<MessageResponse> postResponse2 = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/auth/signup", request, MessageResponse.class);
		
		assertNotNull(postResponse2);
		
		assertNotNull(postResponse2.getBody());
		
		assertEquals("Error: Email is already in use!", postResponse2.getBody().getMessage());
		
	}
	
	@Test
	public void testAuthenticateUser() {
		
		LoginRequest request = new LoginRequest();
		request.setEmail("junitTest@gmail.com");
		request.setPassword("1234567");
		
		ResponseEntity<JwtResponse> postResponse = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/auth/signin", request, JwtResponse.class);
		
		assertNotNull(postResponse);
		
		assertNotNull(postResponse.getBody());
		
		assertEquals(request.getEmail(), postResponse.getBody().getEmail());
		
	}
	

}
