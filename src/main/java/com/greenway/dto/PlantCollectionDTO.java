package com.greenway.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class PlantCollectionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4659491342089331372L;
	
	private Long collectionId;
	
	private Long plantId;
	
	private Long userId;
	
	private String imageUrl;
	
	private String growthStage;
	
	private String description;
	
	private boolean availableForExchange;
	
	private String botanicalName;
	
	private String commonName;

	private String plantType;
	
	private String lightLevel;
	
	private String waterNeeds;
	
	private String maintenance;
	
	private String soilType;
	
	public PlantCollectionDTO() {
		
	}
	
	public PlantCollectionDTO(Long collectionId,
							  Long plantId,
							  Long userId,
							  String imageUrl,
							  String growthStage,
							  String description,
							  Boolean availableForExchange,
							  String botanicalName,
							  String commonName,
							  String plantType,
							  String lightLevel,
							  String soilType,
							  String waterNeeds,
							  String maintenance) {
		
		this.collectionId = collectionId;
		this.plantId = plantId;
		this.userId = userId;
		this.imageUrl = imageUrl;
		this.growthStage = growthStage;
		this.description = description;
		this.availableForExchange = availableForExchange;
		this.botanicalName = botanicalName;
		this.commonName = commonName;
		this.plantType = plantType;
		this.lightLevel = lightLevel;
		this.soilType = soilType;
		this.waterNeeds = waterNeeds;
		this.maintenance = maintenance;
	}

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

	public boolean isAvailableForExchange() {
		return availableForExchange;
	}

	public void setAvailableForExchange(boolean availableForExchange) {
		this.availableForExchange = availableForExchange;
	}

	public String getBotanicalName() {
		return botanicalName;
	}

	public void setBotanicalName(String botanicalName) {
		this.botanicalName = botanicalName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getPlantType() {
		return plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public String getLightLevel() {
		return lightLevel;
	}

	public void setLightLevel(String lightLevel) {
		this.lightLevel = lightLevel;
	}

	public String getWaterNeeds() {
		return waterNeeds;
	}

	public void setWaterNeeds(String waterNeeds) {
		this.waterNeeds = waterNeeds;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	public String getSoilType() {
		return soilType;
	}

	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	
}
