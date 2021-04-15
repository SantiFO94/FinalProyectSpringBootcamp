package com.santi.recetarium.models.entities.responses;

import com.santi.recetarium.models.entities.dto.UserDTOPublic;

public class ResponseUserDTOPublic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDTOPublic userPublic;
	
	public ResponseUserDTOPublic(UserDTOPublic userPublic) {
		this.userPublic = userPublic;
	}

	public UserDTOPublic getUserPublic() {
		return userPublic;
	}

}
