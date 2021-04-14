package com.santi.recetarium.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.entity.Recipes;
import com.santi.recetarium.models.entity.dao.IRecipeDAO;

@Service
public class IRecipeServiceIMPL implements IRecipeService {

	@Autowired
	private IRecipeDAO recipeDAO;
	
	@Override
	public List<Recipes> findAll() {
		return (List<Recipes>)recipeDAO.findAll();
	}

	@Override
	public Recipes findById(Integer id) {
		return recipeDAO.findById(id).orElse(null);
	}

	@Override
	public Recipes save(Recipes ingredient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Recipes tarea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

}
