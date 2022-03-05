package com.greenway.payload.request;

public class CollectionProfileRequest {
	private Long collectionId;
	
	private Long plantId;
	
	private String imageUrl;
    
    private String growthStage;
    
    private String description;
    
    private Boolean avaiable;

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

	public Boolean getAvaiable() {
		return avaiable;
	}

	public void setAvaiable(Boolean avaiable) {
		this.avaiable = avaiable;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
