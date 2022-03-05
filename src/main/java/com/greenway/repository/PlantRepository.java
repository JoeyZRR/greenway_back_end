package com.greenway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenway.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

  List<Plant> findAll();

  /*
  PlantMatcher matcher = PlantMatcher
    .matchingAll()
    .withMatcher("botanicalName", contains().ignoreCase())
    .withMatcher("commonName", contains().ignoreCase());
  Plant example = Plant
    .builder()
    .botanicalName(botanicalName)
    .commonName(commonName)
    .plantType(plantType)
    .lightLevel(lightLevel)
    .waterNeeds(waterNeeds)
    .maintenance(maintenance)
    .soilType(soilType)
    .build();
  return repository.findAll(Example.of(example, matcher));
  */

}
