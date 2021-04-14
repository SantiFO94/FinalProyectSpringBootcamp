package com.santi.recetarium.models.entity.response;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entity.dto.RecipeDTOProfile;

public class ResponseRecipesDTOProfile {

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
