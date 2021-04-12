package com.santi.recetarium.entity.response;

import com.santi.recetarium.entity.Ingredient;

//usar para acceder rápidamente a las cantidades de un ingrediente dentro de una receta
public class ResponseIngredient {

	private Ingredient ingredient;
	
	public ResponseIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
}
