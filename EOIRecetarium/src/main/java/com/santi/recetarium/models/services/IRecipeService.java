package com.santi.recetarium.models.services;

import java.util.List;

import com.santi.recetarium.models.entity.Recipes;


public interface IRecipeService {

	public List<Recipes> findAll();

    public Recipes findById(Integer id);

    public Recipes save(Recipes ingredient);

    public void delete(Recipes tarea);

    public void deleteById(Integer id);
}
