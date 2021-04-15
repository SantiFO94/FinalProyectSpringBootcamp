package com.santi.recetarium.models.entities.responses;

import com.santi.recetarium.models.entities.dto.IngredientDTOListless;

public class ResponseIngredientDTOListless implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
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
