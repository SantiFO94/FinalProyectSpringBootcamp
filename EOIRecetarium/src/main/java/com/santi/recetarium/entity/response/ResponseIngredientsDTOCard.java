package com.santi.recetarium.entity.response;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.entity.dto.IngredientDTOCard;

public class ResponseIngredientsDTOCard {

	private List<IngredientDTOCard> ingredients = new ArrayList<IngredientDTOCard>();
	
	public ResponseIngredientsDTOCard(List<IngredientDTOCard> ingredients) {
		this.ingredients = ingredients;
	}

	public List<IngredientDTOCard> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTOCard> ingredients) {
		this.ingredients = ingredients;
	}
	
}
