package com.santi.recetarium.models.entity.dto;

import com.santi.recetarium.models.entity.Recipe;

public class RecipeDTOProfile {

	private String recipeName;
	private String description;
	private int difficulty;
	private String image;
//	private Set<Ingredients> ingredients = new HashSet<Ingredients>(0);
	
	public RecipeDTOProfile(Recipe recipe) {
		this.recipeName= recipe.getRecipeName();
		this.description = recipe.getDescription();
		this.difficulty = recipe.getDifficulty();
		this.image = recipe.getImage();
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

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public Set<Ingredients> getIngredients() {
//		return ingredients;
//	}
//
//	public void setIngredients(Set<Ingredients> ingredients) {
//		this.ingredients = ingredients;
//	}
	
	
}
