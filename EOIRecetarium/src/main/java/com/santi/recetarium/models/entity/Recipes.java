package com.santi.recetarium.models.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipes implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_recipe", unique = true, nullable = false)
	private int idRecipe;
	@Column(name = "recipe_name", nullable = false)
	private String recipeName;
	@Column(name = "dificulty")
	private String difficulty;
	@Column(name = "time_required")
	private String timeRequired;
	@Column(name = "description")
	private String description;
	@Column(name = "instructions", nullable = false)
	private String instructions;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipe_ingredient", joinColumns = {
			@JoinColumn(name = "id_recipe", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_ingredient", nullable = false, updatable = false) })
	private Set<Ingredients> ingredients = new HashSet<Ingredients>(0);

	public Recipes() {
	}

	public Recipes(int idRecipe, String name, String instructions) {
		this.idRecipe = idRecipe;
		this.recipeName = name;
		this.instructions = instructions;
	}

	public Recipes(int idRecipe, String name, String dificulty, String timeRequired, String description,
			String instructions, Set<Ingredients> ingredientses) {
		this.idRecipe = idRecipe;
		this.recipeName = name;
		this.difficulty = dificulty;
		this.timeRequired = timeRequired;
		this.description = description;
		this.instructions = instructions;
		this.ingredients = ingredientses;
	}

	
	public int getIdRecipe() {
		return this.idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public String getRecipeName() {
		return this.recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	
	public String getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTimeRequired() {
		return this.timeRequired;
	}

	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Set<Ingredients> getIngredientses() {
		return this.ingredients;
	}

	public void setIngredientses(Set<Ingredients> ingredientses) {
		this.ingredients = ingredientses;
	}

}
