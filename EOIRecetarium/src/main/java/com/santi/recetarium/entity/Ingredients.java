package com.santi.recetarium.entity;
// Generated 13 abr. 2021 11:28:27 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ingredients")
public class Ingredients implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long idIngredient;
	private String name;
	private Double quantity;
	private String measure;
	private Set recipeIngredients = new HashSet(0);
	private Recipes recipes;

	public Ingredients() {
	}

	public Ingredients(long idIngredient, String name, String measure) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.measure = measure;
	}

	public Ingredients(long idIngredient, String name, Double quantity, String measure, Set recipeIngredients,
			Recipes recipes) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.quantity = quantity;
		this.measure = measure;
		this.recipeIngredients = recipeIngredients;
		this.recipes = recipes;
	}

	@Id

	@Column(name = "id_ingredient", unique = true, nullable = false)
	public long getIdIngredient() {
		return this.idIngredient;
	}

	public void setIdIngredient(long idIngredient) {
		this.idIngredient = idIngredient;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "quantity", precision = 17, scale = 17)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "measure", nullable = false, length = 50)
	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")
	public Set getRecipeIngredients() {
		return this.recipeIngredients;
	}

	public void setRecipeIngredients(Set recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ingredients")
	public Recipes getRecipes() {
		return this.recipes;
	}

	public void setRecipes(Recipes recipes) {
		this.recipes = recipes;
	}

}
