package com.santi.recetarium.models.services;

import java.util.List;

import com.santi.recetarium.models.entities.Recipe;


public interface IRecipeService {

	public List<Recipe> findAll();

    public Recipe findById(Integer id);

    public Recipe save(Recipe recipe);

    public void delete(Recipe recipe);

    public void deleteById(Integer id);
}
