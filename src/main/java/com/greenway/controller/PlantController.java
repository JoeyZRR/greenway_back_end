package com.greenway.controller;

import java.util.Optional;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenway.model.Plant;
// import com.greenway.payload.request.PlantRequest;
import com.greenway.repository.PlantRepository;

@RestController
@RequestMapping("/api/v1/plants/")
public class PlantController{

	@Autowired
	PlantRepository plantRepository;

	@GetMapping()
	public List<Plant> retrieveAllPlants() {
		return plantRepository.findAll();
	}

}
