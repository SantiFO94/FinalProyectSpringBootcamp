package com.santi.recetarium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.santi.recetarium.entity.Ingredient;
import com.santi.recetarium.entity.dao.IIngredientDAO;

public class IIngredientServiceIMPL implements IIngredientService {

	@Autowired
	private IIngredientDAO ingredientDAO;
	
	@Override
	public List<Ingredient> findAll() {
		return (List<Ingredient>)ingredientDAO.findAll();
	}

	@Override
	public Ingredient findById(Long id) {
		return ingredientDAO.findById(id).orElse(null);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		return ingredientDAO.save(ingredient);
	}

	@Override
	public void delete(Ingredient ingredient) {
		ingredientDAO.delete(ingredient);
	}

	@Override
	public void deleteById(Long id) {
		ingredientDAO.deleteById(id);
	}

}
