package com.santi.recetarium.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.dao.IRecipeDAO;
import com.santi.recetarium.models.entities.Recipe;

@Service
public class IRecipeServiceIMPL implements IRecipeService {

	@Autowired
	private IRecipeDAO recipeDAO;
	
	@Override
	public List<Recipe> findAll() {
		return (List<Recipe>)recipeDAO.findAll();
	}

	@Override
	public Recipe findById(Integer id) {
		return recipeDAO.findById(id).orElse(null);
	}

	@Override
	public Recipe save(Recipe ingredient) {
		return recipeDAO.save(ingredient);
	}

	@Override
	public void delete(Recipe ingredient) {
		recipeDAO.delete(ingredient);
	}

	@Override
	public void deleteById(Integer id) {
		recipeDAO.deleteById(id);
	}

}
