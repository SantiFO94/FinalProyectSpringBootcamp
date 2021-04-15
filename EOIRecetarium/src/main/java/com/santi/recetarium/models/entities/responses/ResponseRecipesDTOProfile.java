package com.santi.recetarium.models.entities.responses;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entities.dto.RecipeDTOProfile;

public class ResponseRecipesDTOProfile implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<RecipeDTOProfile> recipe = new ArrayList<>();
	
	public ResponseRecipesDTOProfile(List<RecipeDTOProfile> recipe) {
		this.recipe = recipe;
	}

	public List<RecipeDTOProfile> getRecipe() {
		return recipe;
	}

	public void setRecipe(List<RecipeDTOProfile> recipe) {
		this.recipe = recipe;
	}
	
	
}
