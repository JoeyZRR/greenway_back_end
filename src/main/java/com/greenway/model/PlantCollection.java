package com.greenway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table( name = "plant_collection")
public class PlantCollection{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long collectionId;
	
	private Long plantId;
	
	@Size(max = 100)
	private String imageUrl;
    
	@Size(max=50)
    private String growthStage;
    
	@Size(max=250)
    private String description;
    
	@Column(columnDefinition="bit default 0")
    private Boolean available = false;

    private Long userId;
    
	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public Long getPlantId() {
		return plantId;
	}

	public void setPlantId(Long plantId) {
		this.plantId = plantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGrowthStage() {
		return growthStage;
	}

	public void setGrowthStage(String growthStage) {
		this.growthStage = growthStage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
