package com.santi.recetarium.models.entity.dto;

import com.santi.recetarium.models.entity.Ingredient;

public class IngredientDTOCard {

	private String name;
	
	public IngredientDTOCard(Ingredient ingredient) {
		this.name = ingredient.getIngredientName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
