package com.santi.recetarium.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.dao.IRecipeDAO;
import com.santi.recetarium.models.entities.Recipe;
import com.santi.recetarium.models.entities.dto.RecipeDTOIngredientListless;

@Service
public class IRecipeServiceIMPL implements IRecipeService {

	@Autowired
	private IRecipeDAO recipeDAO;
	@Autowired
	private IUserServiceIMPL userService;
	@Autowired
	private IIngredientServiceIMPL ingredientService;
	
	@Override
	public List<Recipe> findAll() {
		return (List<Recipe>)recipeDAO.findAll();
	}

	@Override
	public Recipe findById(Integer id) {
		return recipeDAO.findById(id).orElse(null);
	}

	@Override
	public Recipe save(Recipe recipe) {
		return recipeDAO.save(recipe);
	}

	@Override
	public void delete(Recipe recipe) {
		recipeDAO.delete(recipe);
	}

	@Override
	public void deleteById(Integer id) {
		recipeDAO.deleteById(id);
	}

	public Recipe insert(RecipeDTOIngredientListless recipeDto, Integer idOwner) {
		Recipe recipe = new Recipe();
		recipe.setRecipeName(recipeDto.getRecipeName());
		recipe.setDifficulty(recipeDto.getDifficulty());
		recipe.setTimeRequired(recipeDto.getTimeRequired());
		recipe.setDescription(recipeDto.getDescription());
		recipe.setInstructions(recipeDto.getInstructions());
		recipe.setImage(recipeDto.getImage());
		recipeDto.getIngredients().forEach(i-> 
			recipe.getIngredients()
			.add(ingredientService.findById(i.getIdIngredient())) );
		recipe.setUser(userService.findById(idOwner));
		//return recipeDAO.save(recipe);
		return recipe;
	}
}
