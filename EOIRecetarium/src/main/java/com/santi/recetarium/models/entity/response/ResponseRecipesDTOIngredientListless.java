package com.santi.recetarium.models.entity.response;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entity.dto.RecipeDTOIngredientListless;

public class ResponseRecipesDTOIngredientListless {

	private List<RecipeDTOIngredientListless> recipes = new ArrayList<>();
	
	public ResponseRecipesDTOIngredientListless(List<RecipeDTOIngredientListless> recipes) {
		this.recipes = recipes;
	}

	public List<RecipeDTOIngredientListless> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeDTOIngredientListless> recipes) {
		this.recipes = recipes;
	}
}
