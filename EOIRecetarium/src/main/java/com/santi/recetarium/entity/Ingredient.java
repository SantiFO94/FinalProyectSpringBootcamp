package com.santi.recetarium.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long idIngredient;
	private String name;
	private Double quantity;
	private String measure;

	public Ingredient() {
	}

	public Ingredient(long idIngredient, String name, String measure) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.measure = measure;
	}

	public Ingredient(long idIngredient, String name, Double quantity, String measure) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.quantity = quantity;
		this.measure = measure;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

}
