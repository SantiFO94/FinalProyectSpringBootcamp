package com.santi.recetarium.models.entity.dto;

import com.santi.recetarium.models.entity.Ingredients;

public class IngredientDTOListless {

	private int idIngredient;
	private String name;
	
	public IngredientDTOListless(Ingredients ingredient) {
		this.idIngredient = ingredient.getIdIngredient();
		this.name = ingredient.getName();
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
