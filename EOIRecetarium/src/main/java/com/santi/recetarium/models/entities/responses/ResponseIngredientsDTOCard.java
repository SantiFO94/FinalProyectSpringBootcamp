package com.santi.recetarium.models.entities.responses;

import java.util.ArrayList;
import java.util.List;

import com.santi.recetarium.models.entities.dto.IngredientDTOCard;

public class ResponseIngredientsDTOCard implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
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
