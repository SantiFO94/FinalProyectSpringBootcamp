package com.santi.recetarium.models.services;

import java.util.List;

import com.santi.recetarium.models.entity.Recipe;


public interface IRecipeService {

	public List<Recipe> findAll();

    public Recipe findById(Integer id);

    public Recipe save(Recipe ingredient);

    public void delete(Recipe tarea);

    public void deleteById(Integer id);
}
