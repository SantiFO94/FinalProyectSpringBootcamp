package com.santi.recetarium.models.entities.responses;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entities.dto.IngredientDTOListless;

public class ResponseIngredientsDTOListless implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<IngredientDTOListless> ingredients = new ArrayList<>();
	
	public ResponseIngredientsDTOListless(List<IngredientDTOListless> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<IngredientDTOListless> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTOListless> ingredients) {
		this.ingredients = ingredients;
	}
}
