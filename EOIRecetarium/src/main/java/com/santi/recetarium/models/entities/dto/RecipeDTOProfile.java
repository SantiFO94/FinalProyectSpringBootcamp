package com.santi.recetarium.models.entities.dto;

import com.santi.recetarium.models.entities.Recipe;

public class RecipeDTOProfile {

	private int idRecipe;
	private String recipeName;
	private String description;
	private int difficulty;
	private String image;
	
	public RecipeDTOProfile(Recipe recipe) {
		this.idRecipe = recipe.getIdRecipe();
		this.recipeName = recipe.getRecipeName();
		this.description = recipe.getDescription();
		this.difficulty = recipe.getDifficulty();
		this.image = recipe.getImage();
	}

	
	public int getIdRecipe() {
		return idRecipe;
	}


	public String getRecipeName() {
		return recipeName;
	}

	public String getDescription() {
		return description;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public String getImage() {
		return image;
	}

}
