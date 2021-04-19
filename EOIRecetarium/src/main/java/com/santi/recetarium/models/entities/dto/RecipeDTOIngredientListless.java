package com.santi.recetarium.models.entities.dto;

import java.util.HashSet;
import java.util.Set;

import com.santi.recetarium.models.entities.Recipe;

public class RecipeDTOIngredientListless {

	private String recipeName;
	private int difficulty;
	private String timeRequired;
	private String description;
	private String instructions;
	private String image;
	private Set<IngredientDTOListless> ingredients = new HashSet<IngredientDTOListless>(0);
	
	public RecipeDTOIngredientListless(Recipe recipe) {
		this.recipeName= recipe.getRecipeName();
		this.difficulty = recipe.getDifficulty();
		this.timeRequired = recipe.getTimeRequired();
		this.description = recipe.getDescription();
		this.instructions = recipe.getInstructions();
		this.image = recipe.getImage();
		recipe.getIngredients().forEach(i->this.ingredients
				.add(new IngredientDTOListless(i)));

	}

	public String getRecipeName() {
		return recipeName;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getTimeRequired() {
		return timeRequired;
	}

	public String getDescription() {
		return description;
	}

	public String getInstructions() {
		return instructions;
	}

	public String getImage() {
		return image;
	}

	public Set<IngredientDTOListless> getIngredients() {
		return ingredients;
	}

}
