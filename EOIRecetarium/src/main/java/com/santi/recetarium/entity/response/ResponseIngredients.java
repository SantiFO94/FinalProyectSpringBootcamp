package com.santi.recetarium.entity.response;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.entity.Ingredient;

public class ResponseIngredients {

	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public ResponseIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
