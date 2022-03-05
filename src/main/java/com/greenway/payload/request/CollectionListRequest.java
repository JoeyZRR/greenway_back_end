package com.greenway.payload.request;

import java.util.List;

import com.greenway.model.PlantCollection;


public class CollectionListRequest {
	private List<PlantCollection> selectedList;

	public List<PlantCollection> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<PlantCollection> selectedList) {
		this.selectedList = selectedList;
	}
    
}