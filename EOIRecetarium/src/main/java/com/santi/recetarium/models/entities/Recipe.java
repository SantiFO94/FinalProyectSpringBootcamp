package com.santi.recetarium.models.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "recipes", uniqueConstraints = @UniqueConstraint(columnNames = "recipe_name"))
public class Recipe implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_recipe", unique = true, nullable = false)
	private int idRecipe;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(name = "recipe_name", unique = true, nullable = false, length = 100)
	private String recipeName;
	
	@Column(name = "difficulty")
	private int difficulty;
	
	@Column(name = "time_required", length = 100)
	private String timeRequired;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "instructions", nullable = false)
	private String instructions;
	
	@Column(name = "image")
	private String image;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "recipe_ingredient", joinColumns = {
			@JoinColumn(name = "id_recipe", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_ingredient", nullable = false, updatable = false) })
	private Set<Ingredient> ingredients = new HashSet<Ingredient>(0);
	
	public Recipe() {
		
	}
	/**
	 * Constructor para actualizar una receta existente con una nueva
	 * 
	 * @param idRecipe identificador de la receta que se quiere actualizar
	 * @param recipe body que contiene los nuevos datos
	 */
	public Recipe(int idRecipe, Recipe recipe) {
		this.idRecipe = idRecipe;
		this.user = recipe.getUser();
		this.recipeName = recipe.getRecipeName();
		this.difficulty = recipe.getDifficulty();
		this.timeRequired = recipe.getTimeRequired();
		this.description = recipe.getDescription();
		this.instructions = recipe.getInstructions();
		this.image = recipe.getImage();
		this.ingredients = recipe.getIngredients();
	}
	
	public Recipe(int idRecipe, String name, int difficulty, String timeRequired, String description,
			String instructions, User user, String image, Set<Ingredient> ingredients) {
		this.idRecipe = idRecipe;
		this.user = user;
		this.recipeName = name;
		this.difficulty = difficulty;
		this.timeRequired = timeRequired;
		this.description = description;
		this.instructions = instructions;
		this.image = image;
		this.ingredients = ingredients;
	}

	
	public int getIdRecipe() {
		return this.idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRecipeName() {
		return this.recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(int difficulty) {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
