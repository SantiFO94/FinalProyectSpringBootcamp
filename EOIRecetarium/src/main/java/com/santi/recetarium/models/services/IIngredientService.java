package com.santi.recetarium.models.services;

import java.util.List;

import com.santi.recetarium.models.entity.Ingredients;


public interface IIngredientService {
	
	public List<Ingredients> findAll();

    public Ingredients findById(Integer id);

    public Ingredients save(Ingredients ingredient);

    public void delete(Ingredients tarea);

    public void deleteById(Integer id);
}
