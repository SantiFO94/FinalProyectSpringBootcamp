package com.santi.recetarium.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.entity.Ingredient;
import com.santi.recetarium.models.entity.dao.IIngredientDAO;

@Service
public class IIngredientServiceIMPL implements IIngredientService {

	@Autowired
	private IIngredientDAO ingredientDAO;
	
	@Override
	public List<Ingredient> findAll() {
		return (List<Ingredient>)ingredientDAO.findAll();
	}

	@Override
	public Ingredient findById(Integer id) {
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
	public void deleteById(Integer id) {
		ingredientDAO.deleteById(id);
	}

}
