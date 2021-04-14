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
public class Ingredients implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_ingredient", unique = true, nullable = false)
	private int idIngredient;
	@Column(name = "ingredient_name", nullable = false)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")
	private Set<Recipes> recipes = new HashSet<Recipes>(0);

	public Ingredients() {
	}

	public Ingredients(int idIngredient, String name) {
		this.idIngredient = idIngredient;
		this.name = name;
	}

	public Ingredients(int idIngredient, String name, Set<Recipes> recipeses) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.recipes = recipeses;
	}

	
	public int getIdIngredient() {
		return this.idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<Recipes> getRecipeses() {
		return this.recipes;
	}

	public void setRecipeses(Set<Recipes> recipeses) {
		this.recipes = recipeses;
	}

}
