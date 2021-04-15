package com.santi.recetarium.models.entities.responses;

import java.io.Serializable;
import java.util.List;

import com.santi.recetarium.models.entities.dto.UserDTOPublic;

public class ResponseUsersDTOPublic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<UserDTOPublic> userPublic;
	
	public ResponseUsersDTOPublic(List<UserDTOPublic> userPublic) {
		this.userPublic = userPublic;
	}

	public List<UserDTOPublic> getUserPublic() {
		return userPublic;
	}

	
}
