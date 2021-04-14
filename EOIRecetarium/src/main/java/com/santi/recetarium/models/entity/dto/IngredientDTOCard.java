package com.santi.recetarium.models.entity.dto;

import com.santi.recetarium.models.entity.Ingredients;

public class IngredientDTOCard {

	private String name;
	
	public IngredientDTOCard(Ingredients ingredient) {
		this.name = ingredient.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
