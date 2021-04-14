package com.santi.recetarium.models.entity.response;

import com.santi.recetarium.models.entity.dto.IngredientDTOListless;

public class ResponseIngredientDTOListless {

	private IngredientDTOListless ingredient;
	
	public ResponseIngredientDTOListless(IngredientDTOListless ingredient) {
		this.ingredient = ingredient;
	}
	
	public IngredientDTOListless getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientDTOListless ingredient) {
		this.ingredient = ingredient;
	}
}
