package com.greenway.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenway.dto.PlantCollectionDTO;
import com.greenway.model.PlantCollection;

@Repository
public interface CollectionRepository extends JpaRepository<PlantCollection, Long> {
	
	Optional<PlantCollection> findByCollectionId(Long collectionId);

	Boolean existsByCollectionId(Long collectionId);
	
	List<PlantCollection> findAll();
	
	@Query("select new com.greenway.dto.PlantCollectionDTO("
			+ "pc.collectionId, p.plantId, pc.userId, pc.imageUrl, pc.growthStage, pc.description, pc.available, "
			+ "p.botanicalName, p.commonName, p.plantType, p.soilType, p.lightLevel, p.waterNeeds, p.maintenance " 
			+ ")  from PlantCollection pc "
			+ "join Plant p on pc.plantId = p.plantId  where pc.userId=?1")
	List<PlantCollectionDTO> getUsersCollection(Long userId);
	
}


