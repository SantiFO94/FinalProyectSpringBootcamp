package com.santi.recetarium.models.entity.dto;

import com.santi.recetarium.models.entity.Recipes;

public class RecipeDTOProfile {

	private String recipeName;
	private String description;
	private String difficulty;
//	private Set<Ingredients> ingredients = new HashSet<Ingredients>(0);
	
	public RecipeDTOProfile() {
		
	}
	
	public RecipeDTOProfile(Recipes recipe) {
		this.recipeName= recipe.getRecipeName();
		this.description = recipe.getDescription();
		this.difficulty = recipe.getDifficulty();
//		this.ingredients = recipe.getIngredientses();
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

//	public Set<Ingredients> getIngredients() {
//		return ingredients;
//	}
//
//	public void setIngredients(Set<Ingredients> ingredients) {
//		this.ingredients = ingredients;
//	}
	
	
}
