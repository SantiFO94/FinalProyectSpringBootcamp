package com.santi.recetarium.models.entities.dto;

import com.santi.recetarium.models.entities.Ingredient;

public class IngredientDTOListless {

	private int idIngredient;
	private String name;
	
	public IngredientDTOListless(Ingredient ingredient) {
		this.idIngredient = ingredient.getIdIngredient();
		this.name = ingredient.getIngredientName();
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public String getName() {
		return name;
	}

}
