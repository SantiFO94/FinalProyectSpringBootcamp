package com.santi.recetarium.models.entities.responses;

import java.util.List;

import com.santi.recetarium.models.entities.dto.UserDTOIngredientListless;

public class ResponseUsersDTOIngredientListless implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<UserDTOIngredientListless> usersListless;
	
	public ResponseUsersDTOIngredientListless(List<UserDTOIngredientListless> usersListless) {
		this.usersListless = usersListless;
	}

	public List<UserDTOIngredientListless> getUsersListless() {
		return usersListless;
	}
	
}
