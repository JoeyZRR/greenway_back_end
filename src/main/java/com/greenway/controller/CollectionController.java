package com.greenway.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenway.dto.PlantCollectionDTO;
import com.greenway.model.PlantCollection;
import com.greenway.model.User;
import com.greenway.payload.request.CollectionListRequest;
import com.greenway.payload.request.CollectionProfileRequest;
import com.greenway.payload.response.CollectionResponse;
import com.greenway.payload.response.MessageResponse;
import com.greenway.repository.CollectionRepository;
import com.greenway.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/profiles/")


public class CollectionController {

	@Autowired
	CollectionRepository collectionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Add's the plants selected by the user to the user's collection
	 * @param userId
	 * @param collectionListRequest
	 * @author jipeng
	 */
	@PostMapping("{id}/collection")
	public ResponseEntity<?> addCollection(@Valid @PathVariable("id") Long userId, @RequestBody CollectionListRequest collectionListRequest)
	{	
		
		for(int i=0; i < collectionListRequest.getSelectedList().size(); i++ )
		{
			PlantCollection collection = new PlantCollection();
			collection.setPlantId(collectionListRequest.getSelectedList().get(i).getPlantId());
			collection.setImageUrl(collectionListRequest.getSelectedList().get(i).getImageUrl());
			collection.setUserId(userId);
			collectionRepository.save(collection);
		}
			
		return ResponseEntity.ok(new MessageResponse("Collection(s) added successfully!"));
			
	}
	
	/**
	 * Updates user's plant collection information
	 * @param collectionId
	 * @param collectionProfileRequest
	 * @author jipeng
	 */
	@PutMapping("{userId}/collection/{collectionId}")
	public ResponseEntity<?> saveCollectionProfile(@Valid @PathVariable("collectionId") Long collectionId,
													@RequestBody CollectionProfileRequest collectionProfileRequest)
	{
		
		Optional<PlantCollection> collectionOptional = collectionRepository.findById(collectionId);
		
		if(!collectionOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}else{
			
			PlantCollection collection = collectionOptional.get();
			if(collectionProfileRequest.getGrowthStage() != null) {
				collection.setGrowthStage(collectionProfileRequest.getGrowthStage());
			}
			if(collectionProfileRequest.getDescription() != null) {
				collection.setDescription(collectionProfileRequest.getDescription());
			}
			collectionRepository.save(collection);
			
			return ResponseEntity.ok(collection);
		
		}
	
	}
	
	/**
	 * Gets the user's plants collection
	 * @param userId
	 * @return
	 * @author anniekhand
	 */
	@GetMapping("{id}/collection")
	public ResponseEntity<?> getCollection(@Valid @PathVariable("id") Long userId){
		

		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}else{
			
		List<PlantCollectionDTO> collectionList = collectionRepository.getUsersCollection(userId);
		
		return ResponseEntity.ok(new CollectionResponse(collectionList));
		}
	}
	
	/**
	 * Deletes the user's individual plant collection
	 * @param collectionId
	 * @return
	 * @author jipeng
	 */
	@DeleteMapping("{id}/collection/{collectionId}")
	public ResponseEntity<?> deleteCollection(@Valid @PathVariable("collectionId") Long collectionId)	
	{
		Optional<PlantCollection> collectionOptional = collectionRepository.findByCollectionId(collectionId);
		if(!collectionOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
		
			collectionRepository.deleteById(collectionId);
		
			return ResponseEntity.ok(new MessageResponse("Collection deleted successfully!"));
		}
	}	
}
