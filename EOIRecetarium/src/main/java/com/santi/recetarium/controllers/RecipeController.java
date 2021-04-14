package com.santi.recetarium.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.recetarium.models.entity.Recipes;
import com.santi.recetarium.models.entity.dto.RecipeDTOIngredientListless;
import com.santi.recetarium.models.entity.dto.RecipeDTOProfile;
import com.santi.recetarium.models.entity.response.ResponseRecipeDTOIngredientListless;
import com.santi.recetarium.models.entity.response.ResponseRecipesDTOIngredientListless;
import com.santi.recetarium.models.entity.response.ResponseRecipesDTOProfile;
import com.santi.recetarium.models.services.IRecipeServiceIMPL;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recipes")
public class RecipeController {
//Aqui los metodos que devuelven objeto individual estan con Response del DTO
	@Autowired
	private IRecipeServiceIMPL recipeService;
	
	/**
	 * Recuperar todas las recetas completas.
	 * @return ResponseEntity<Map<String, Object>> con mensaje de error en caso de que ocurra algún problema
	 * @return ResponseEntity<ResponseRecipes> con la lista de recetas recuperadas
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getAllRecipes(){
		
		List<Recipes> recipes = new ArrayList<Recipes>();
		Map<String, Object> responseError = new HashMap();
		
		try {
			recipes = recipeService.findAll();
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(recipes.size()==0) {
			responseError.put("mensaje", "No existen recetas");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		List<RecipeDTOIngredientListless> recipesIngredientsListless = new ArrayList<>();
		recipes.forEach(r->recipesIngredientsListless.add(new RecipeDTOIngredientListless(r)));
		ResponseRecipesDTOIngredientListless responseRecipe = new ResponseRecipesDTOIngredientListless(recipesIngredientsListless);
		
		return new ResponseEntity<ResponseRecipesDTOIngredientListless>(responseRecipe, HttpStatus.OK);
	}
	
	//sacar resumen de recetas para presentar en el perfil de usuario
	@GetMapping("/all/summaries")
	public ResponseEntity<?> getRecipesSummary(){
		
		List<Recipes> recipes = new ArrayList<Recipes>();
		Map<String, Object> responseError = new HashMap();
		
		try {
			recipes = recipeService.findAll();
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(recipes.size()==0) {
			responseError.put("mensaje", "No existen ingredientes");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		List<RecipeDTOProfile> recipeProfile = new ArrayList<RecipeDTOProfile>();
		recipes.forEach(r-> recipeProfile.add(new RecipeDTOProfile(r)));
		ResponseRecipesDTOProfile responseRecipe = new ResponseRecipesDTOProfile(recipeProfile);
		
		return new ResponseEntity<ResponseRecipesDTOProfile>(responseRecipe, HttpStatus.OK);
	}
	
	//encontrar una receta completa en el perfil 
	@GetMapping("/recipe/{id}")
	public ResponseEntity<?> getRecipe(@PathVariable Integer id){

		Recipes recipe = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			recipe = recipeService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(recipe==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		RecipeDTOIngredientListless recipeIngredientListless = new RecipeDTOIngredientListless(recipe);
		ResponseRecipeDTOIngredientListless responseRecipe = new ResponseRecipeDTOIngredientListless(recipeIngredientListless);
		
		
		return new ResponseEntity<ResponseRecipeDTOIngredientListless>(responseRecipe, HttpStatus.OK);
	}
	
	//agregar validaciones con @Valid y BindingResult result
	@PostMapping("/add")
	public ResponseEntity<?> addRecipe(@RequestBody Recipes recipe){
		
		Recipes newRecipe = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			newRecipe = recipeService.save(recipe);
		}catch(DataAccessException e) {
			responseError.put("mensaje", "Error al intentar insertar en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		RecipeDTOIngredientListless recipeIngredientListless = new RecipeDTOIngredientListless(newRecipe);
		ResponseRecipeDTOIngredientListless responseRecipe = new ResponseRecipeDTOIngredientListless(recipeIngredientListless);

		return new ResponseEntity<ResponseRecipeDTOIngredientListless>(responseRecipe,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRecipe(@RequestBody Recipes recipe, @PathVariable Integer id){

		Recipes recipeOriginal = null;
		Recipes recipeUpdated = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			recipeOriginal = recipeService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al recuperar el ingrediente de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(recipeOriginal==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		try {
			recipeOriginal.setRecipeName(recipe.getRecipeName());
			recipeOriginal.setDifficulty(recipe.getDifficulty());
			recipeOriginal.setTimeRequired(recipe.getTimeRequired());
			recipeOriginal.setDescription(recipe.getDescription());
			recipeOriginal.setInstructions(recipe.getInstructions());
			recipeOriginal.setIngredientses(recipe.getIngredientses());
			recipeUpdated = recipeService.save(recipeOriginal);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al actualizar la receta en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		RecipeDTOIngredientListless recipeIngredientListless = new RecipeDTOIngredientListless(recipeUpdated);
		ResponseRecipeDTOIngredientListless responseRecipe = new ResponseRecipeDTOIngredientListless(recipeIngredientListless);

		return new ResponseEntity<ResponseRecipeDTOIngredientListless>(responseRecipe, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Integer id){
		
		Map<String, Object> responseError = new HashMap();
		
		try {
			recipeService.deleteById(id);
		}catch (DataAccessException e) {
			responseError.put("mensaje", "Error al intentar borrar de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseError.put("mensaje","La receta con id " + id + " ha sido borrada con éxito");

		return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NO_CONTENT);
	}
}
