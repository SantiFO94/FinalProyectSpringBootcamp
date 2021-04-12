package com.santi.recetarium.services;

import java.util.List;

import com.santi.recetarium.entity.Ingredient;


public interface IIngredientService {
	
	public List<Ingredient> findAll();

    public Ingredient findById(Long id);

    public Ingredient save(Ingredient ingredient);

    public void delete(Ingredient tarea);

    public void deleteById(Long id);
}
