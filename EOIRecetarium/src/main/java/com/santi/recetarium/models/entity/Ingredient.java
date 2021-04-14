package com.santi.recetarium.models.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_ingredient", unique = true, nullable = false)
	private int idIngredient;
	@Column(name = "ingredient_name", nullable = false)
	private String ingredientName;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")
	private Set<Recipe> recipes = new HashSet<Recipe>(0);

	/**
	 * Constructor para actualizar un ingrediente existente con uno nuevo
	 * 
	 * @param idIngredient identificador del ingrediente que se quiere actualizar
	 * @param ingredient body que contiene los nuevos datos
	 */
	public Ingredient(int idIngredient, Ingredient ingredient) {
		this.idIngredient = idIngredient;
		this.ingredientName = ingredient.getIngredientName();
	}
	
	public Ingredient(Ingredient ingredient) {
		this.idIngredient = ingredient.getIdIngredient();
		this.ingredientName = ingredient.getIngredientName();
	}

	public Ingredient(int idIngredient, String name, Set<Recipe> recipeses) {
		this.idIngredient = idIngredient;
		this.ingredientName = name;
		this.recipes = recipeses;
	}

	
	public int getIdIngredient() {
		return this.idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	
	public String getIngredientName() {
		return this.ingredientName;
	}

	public void setIngredientName(String name) {
		this.ingredientName = name;
	}

	public Set<Recipe> getRecipes() {
		return this.recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

}
