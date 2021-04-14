package com.santi.recetarium.models.entity.response;

import com.santi.recetarium.models.entity.dto.RecipeDTOIngredientListless;

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
