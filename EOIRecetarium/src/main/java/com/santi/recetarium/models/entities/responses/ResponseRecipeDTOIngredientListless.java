package com.santi.recetarium.models.entities.responses;

import com.santi.recetarium.models.entities.dto.RecipeDTOIngredientListless;

public class ResponseRecipeDTOIngredientListless {

	private RecipeDTOIngredientListless recipe;
	
	public ResponseRecipeDTOIngredientListless(RecipeDTOIngredientListless recipe) {
		this.recipe = recipe;
	}

	public RecipeDTOIngredientListless getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDTOIngredientListless recipe) {
		this.recipe = recipe;
	}
	
}
