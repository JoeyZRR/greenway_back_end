package com.greenway.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "plant")

public class Plant {
  @Id
  @GeneratedValue(strategy =
  GenerationType.IDENTITY)
  private Long plantId;

  @Size(max = 50)
  private String botanicalName;

  @Size(max = 50)
  private String commonName;

  @Size(max = 30)
  private String plantType;

  @Size(max = 30)
  private String lightLevel;

  @Size(max = 20)
  private String waterNeeds;

  @Size(max = 10)
  private String maintenance;

  @Size(max = 30)
  private String soilType;

  @Size(max = 100)
  private String imageUrl;

  public Plant() {
	}

  public Long getPlantId() {
		return plantId;
	}

  public void setPlantId(Long plantId) {
		this.plantId = plantId;
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

  public String getImageUrl() {
		return imageUrl;
	}

  public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}