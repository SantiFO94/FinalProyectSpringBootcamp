package com.santi.recetarium.entity.dto;

import com.santi.recetarium.entity.Ingredient;

public class IngredientDTOCard {

	private String name;
	
	public IngredientDTOCard() {
		
	}
	
	public IngredientDTOCard(Ingredient ingredient) {
		this.name = ingredient.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
