package com.greenway.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenway.aws.s3.service.AWSS3Service;
import com.greenway.model.PlantCollection;
import com.greenway.model.User;
import com.greenway.repository.CollectionRepository;
import com.greenway.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/images")
public class FileController {
	
	@Autowired
	private AWSS3Service awsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CollectionRepository collectionRepository;
	
	@PutMapping("/profile/{id}")
	public ResponseEntity<String> uploadProfileImage(@RequestPart(value="file") MultipartFile file, @PathVariable("id") Long userId) {

		String s3Url = awsService.uploadFile(file);
		if(!StringUtils.isEmpty(s3Url)){
			Optional<User> userOptional = userRepository.findById(userId);
			User user = userOptional.get();
			user.setImageUrl(s3Url);
			userRepository.save(user);
		}
		final String response = s3Url;
        return new ResponseEntity<>(response, HttpStatus.OK);
        
	}
	
	@DeleteMapping("/profile")
	public ResponseEntity<String> deleteProfileImage(@RequestParam String imageUrl) {
		
		final String response = awsService.deleteFileFromS3Bucket(imageUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
        
	}
	
	@PutMapping("/profile/{userId}/collection/{collectionId}")
	public ResponseEntity<String> uploadPlantImage(@RequestPart(value="file") MultipartFile file, @PathVariable("collectionId") Long collectionId) {

		String s3Url = awsService.uploadFile(file);
		
		if(!StringUtils.isEmpty(s3Url)){
			Optional<PlantCollection> collectionOptional = collectionRepository.findById(collectionId);
			PlantCollection collection = collectionOptional.get();
			collection.setImageUrl(s3Url);
			collectionRepository.save(collection);
		}
		final String response = s3Url;
        return new ResponseEntity<>(response, HttpStatus.OK);
        
	}

}
