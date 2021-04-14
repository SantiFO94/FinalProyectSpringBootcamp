package com.santi.recetarium.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.entity.Ingredients;
import com.santi.recetarium.models.entity.dao.IIngredientDAO;

@Service
public class IIngredientServiceIMPL implements IIngredientService {

	@Autowired
	private IIngredientDAO ingredientDAO;
	
	@Override
	public List<Ingredients> findAll() {
		return (List<Ingredients>)ingredientDAO.findAll();
	}

	@Override
	public Ingredients findById(Integer id) {
		return ingredientDAO.findById(id).orElse(null);
	}

	@Override
	public Ingredients save(Ingredients ingredient) {
		return ingredientDAO.save(ingredient);
	}

	@Override
	public void delete(Ingredients ingredient) {
		ingredientDAO.delete(ingredient);
	}

	@Override
	public void deleteById(Integer id) {
		ingredientDAO.deleteById(id);
	}

}
