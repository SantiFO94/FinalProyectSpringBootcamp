package com.santi.recetarium.models.entities.dto;

import com.santi.recetarium.models.entities.Ingredient;

public class IngredientDTOCard {

	private String name;
	
	public IngredientDTOCard(Ingredient ingredient) {
		this.name = ingredient.getIngredientName();
	}

	public String getName() {
		return name;
	}

}
