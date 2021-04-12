package com.santi.recetarium.entity.response;

import com.santi.recetarium.entity.dto.IngredientDTOCard;

public class ResponseIngredient {

	private IngredientDTOCard ingredient;
	
	public ResponseIngredient(IngredientDTOCard ingredient) {
		this.ingredient = ingredient;
	}

	public IngredientDTOCard getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientDTOCard ingredient) {
		this.ingredient = ingredient;
	}
	
}
