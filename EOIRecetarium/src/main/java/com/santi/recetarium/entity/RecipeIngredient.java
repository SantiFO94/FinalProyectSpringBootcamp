package com.santi.recetarium.entity;
// Generated 13 abr. 2021 11:28:27 by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RecipeIngredient generated by hbm2java
 */
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient implements java.io.Serializable {

	private RecipeIngredientId id;
	private Ingredients ingredients;
	private Recipes recipes;
	private double amount;
	private String measure;

	public RecipeIngredient() {
	}

	public RecipeIngredient(RecipeIngredientId id, Ingredients ingredients, Recipes recipes, double amount,
			String measure) {
		this.id = id;
		this.ingredients = ingredients;
		this.recipes = recipes;
		this.amount = amount;
		this.measure = measure;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idRecipe", column = @Column(name = "id_recipe", nullable = false)),
			@AttributeOverride(name = "idIngredient", column = @Column(name = "id_ingredient", nullable = false)) })
	public RecipeIngredientId getId() {
		return this.id;
	}

	public void setId(RecipeIngredientId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ingredient", nullable = false, insertable = false, updatable = false)
	public Ingredients getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(Ingredients ingredients) {
		this.ingredients = ingredients;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_recipe", nullable = false, insertable = false, updatable = false)
	public Recipes getRecipes() {
		return this.recipes;
	}

	public void setRecipes(Recipes recipes) {
		this.recipes = recipes;
	}

	@Column(name = "amount", nullable = false, precision = 17, scale = 17)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "measure", nullable = false)
	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

}
