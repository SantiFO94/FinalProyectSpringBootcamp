package com.santi.recetarium.models.entities.responses;

import java.io.Serializable;

import com.santi.recetarium.models.entities.dto.UserDTOIngredientListless;

public class ResponseUserDTOIngredientListless implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDTOIngredientListless usersListless;
	
	public ResponseUserDTOIngredientListless(UserDTOIngredientListless usersListless) {
		this.usersListless = usersListless;
	}

	public UserDTOIngredientListless getUsersListless() {
		return usersListless;
	}
	
}
