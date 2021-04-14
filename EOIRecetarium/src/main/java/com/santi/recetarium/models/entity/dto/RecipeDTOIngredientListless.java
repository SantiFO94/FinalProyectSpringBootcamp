package com.santi.recetarium.models.entity.dto;

import java.util.HashSet;
import java.util.Set;

import com.santi.recetarium.models.entity.Recipe;

public class RecipeDTOIngredientListless {

	private int idRecipe;
	private String recipeName;
	private int difficulty;
	private String timeRequired;
	private String description;
	private String instructions;
	private String image;
	private Set<IngredientDTOListless> ingredients = new HashSet<IngredientDTOListless>(0);
	
	public RecipeDTOIngredientListless(Recipe recipe) {
		this.idRecipe = recipe.getIdRecipe();
		this.recipeName= recipe.getRecipeName();
		this.difficulty = recipe.getDifficulty();
		this.timeRequired = recipe.getTimeRequired();
		this.description = recipe.getDescription();
		this.instructions = recipe.getInstructions();
		this.image = recipe.getImage();
		recipe.getIngredients().forEach(i->this.ingredients.add(new IngredientDTOListless(i)));

	}

	public int getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<IngredientDTOListless> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientDTOListless> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
