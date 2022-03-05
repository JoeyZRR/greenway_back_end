package com.greenway.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.greenway.model.PlantCollection;
import com.greenway.payload.request.CollectionListRequest;
import com.greenway.payload.request.CollectionProfileRequest;
import com.greenway.payload.request.LoginRequest;
import com.greenway.payload.request.SignupRequest;
import com.greenway.payload.response.CollectionResponse;
import com.greenway.payload.response.JwtResponse;
import com.greenway.payload.response.MessageResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CollectionControllerIntegrationTest {
	
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
		signupRequest.setEmail("Junit_test@gmail.com");
		signupRequest.setPassword("1234567");
		
		ResponseEntity<MessageResponse> signupResponse = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/auth/signup", signupRequest, MessageResponse.class);
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("Junit_test@gmail.com");
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
	public void testAddCollection() {
		
		//Test add new collection

		//set input
	    List<PlantCollection> selectedList = new ArrayList<>();
	    
	    	PlantCollection newCollection = new PlantCollection();
		    newCollection.setPlantId((long) 1);
		    newCollection.setImageUrl("https://greenwayplants.s3.amazonaws.com/Kiwifruit.jpg");
		    selectedList.add(newCollection);
		    
		    newCollection = new PlantCollection();
		    newCollection.setPlantId((long) 2);
		    newCollection.setImageUrl("https://greenwayplants.s3.amazonaws.com/SouthernMaidenhairFern.jpeg");
		    selectedList.add(newCollection);
	    
	    //create request
	    CollectionListRequest newCollectionRequest = new CollectionListRequest();
	    newCollectionRequest.setSelectedList(selectedList);
	    
		HttpEntity<CollectionListRequest> request = new HttpEntity<CollectionListRequest>(newCollectionRequest,getHeaders());
		
		ResponseEntity<MessageResponse> postResponse = testRestTemplate.postForEntity(this.getBaseUrl() + "/api/v1/profiles/1/collection", request, MessageResponse.class);
		
		assertNotNull(postResponse);
		
		assertNotNull(postResponse.getBody());
		
		assertEquals("Collection(s) added successfully!", postResponse.getBody().getMessage());
	}
	
	@Test
	public void testGetCollection() {
		HttpEntity<?> request = new HttpEntity<>("parameters",getHeaders());
		ResponseEntity<CollectionResponse> getResponse = testRestTemplate.exchange(this.getBaseUrl() + "/api/v1/profiles/1/collection", HttpMethod.GET,request, CollectionResponse.class);
		
		assertNotNull(getResponse);
		
		assertEquals(getResponse.getBody().getUsersCollection().size(), 1);
		
		
	}

	@Test
	public void testDeleteCollection() {
		testAddCollection();
		Map <String , Long > parameters = new HashMap <String, Long> ();
		parameters.put("collectionId", (long) 1);
		HttpEntity<?> request = new HttpEntity<>(parameters, getHeaders());
		ResponseEntity<MessageResponse> deleteResponse = testRestTemplate.exchange(this.getBaseUrl()+"/api/v1/profiles/1/collection/1", HttpMethod.DELETE, request,MessageResponse.class);
		
		assertNotNull(deleteResponse);
		
		assertNotNull(deleteResponse.getBody());
		
		assertEquals("Collection deleted successfully!", deleteResponse.getBody().getMessage());
	}
}
