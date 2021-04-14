package com.santi.recetarium.models.services;

import java.util.List;

import com.santi.recetarium.models.entities.Ingredient;


public interface IIngredientService {
	
	public List<Ingredient> findAll();

    public Ingredient findById(Integer id);

    public Ingredient save(Ingredient ingredient);

    public void delete(Ingredient tarea);

    public void deleteById(Integer id);
}
