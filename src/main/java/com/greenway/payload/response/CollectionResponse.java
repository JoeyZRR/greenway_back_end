package com.greenway.payload.response;

import java.io.Serializable;
import java.util.List;

import com.greenway.dto.PlantCollectionDTO;

public class CollectionResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 654551134277549536L;

	private List<PlantCollectionDTO> usersCollection;
	
	public CollectionResponse() {
	}

	public CollectionResponse(List<PlantCollectionDTO> usersCollection) {
		this.usersCollection = usersCollection;
	}

	public List<PlantCollectionDTO> getUsersCollection() {
		return usersCollection;
	}

	public void setUsersCollection(List<PlantCollectionDTO> usersCollection) {
		this.usersCollection = usersCollection;
	}

}
