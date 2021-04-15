package com.santi.recetarium.models.entities.responses;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entities.dto.RecipeDTOIngredientListless;

public class ResponseRecipesDTOIngredientListless implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
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
