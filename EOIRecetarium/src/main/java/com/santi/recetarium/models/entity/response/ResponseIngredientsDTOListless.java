package com.santi.recetarium.models.entity.response;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entity.dto.IngredientDTOListless;

public class ResponseIngredientsDTOListless {

	private List<IngredientDTOListless> ingredients = new ArrayList<>();
	
	public ResponseIngredientsDTOListless(List<IngredientDTOListless> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<IngredientDTOListless> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTOListless> ingredients) {
		this.ingredients = ingredients;
	}
}
