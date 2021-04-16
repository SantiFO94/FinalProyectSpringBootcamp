package com.santi.recetarium.models.entities.dto;

import com.santi.recetarium.models.entities.Ingredient;

public class IngredientDTOListless {

	private int idIngredient;
	private String ingredientName;
	
	public IngredientDTOListless(Ingredient ingredient) {
		this.idIngredient = ingredient.getIdIngredient();
		this.ingredientName = ingredient.getIngredientName();
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public String getIngredientName() {
		return ingredientName;
	}

}
