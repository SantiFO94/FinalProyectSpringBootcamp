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

import com.santi.recetarium.models.entities.Recipe;
import com.santi.recetarium.models.entities.dto.RecipeDTOIngredientListless;
import com.santi.recetarium.models.entities.dto.RecipeDTOProfile;
import com.santi.recetarium.models.entities.responses.ResponseRecipeDTOIngredientListless;
import com.santi.recetarium.models.entities.responses.ResponseRecipesDTOIngredientListless;
import com.santi.recetarium.models.entities.responses.ResponseRecipesDTOProfile;
import com.santi.recetarium.models.services.IRecipeServiceIMPL;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private IRecipeServiceIMPL recipeService;
	
	/**
	 * Recupera todas las recetas completas.
	 * 
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la respuesta conteniendo la lista de recetas recuperadas en caso de que todo vaya bien.
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getAllRecipes(){
		
		List<Recipe> recipes = new ArrayList<Recipe>();
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
	/**
	 * 
	 * Recuperar resumen de las recetas para presentarlas en el perfil de usuario.
	 * Contiene solo nombre, descripción y dificultad de la receta.
	 * 
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la lista de recetas resumidas recuperadas en caso de que todo vaya bien.
	 */
	@GetMapping("/all/summaries")
	public ResponseEntity<?> getRecipesSummary(){
		
		List<Recipe> recipes = new ArrayList<Recipe>();
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
	
	/**
	 * 
	 * Recuperar una receta a partir de su identificador, mostrando todos sus atributos.
	 * 
	 * @param id id asociado como clave primaria a la receta que se quiere recuperar
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la receta recuperada completa en caso de que todo vaya bien.
	 */
	@GetMapping("/recipe/{id}")
	public ResponseEntity<?> getRecipe(@PathVariable Integer id){

		Recipe recipe = null;
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
		
//		RecipeDTOIngredientListless recipeIngredientListless = new RecipeDTOIngredientListless(recipe);
//		ResponseRecipeDTOIngredientListless responseRecipe = new ResponseRecipeDTOIngredientListless(recipeIngredientListless);
		ResponseRecipeDTOIngredientListless responseRecipe = 
				new ResponseRecipeDTOIngredientListless(
						new RecipeDTOIngredientListless(recipe));
		
		return new ResponseEntity<ResponseRecipeDTOIngredientListless>(responseRecipe, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Agrega una receta nueva a la base de datos a partir de sus atributos sin id.
	 * 
	 * @param recipe recipe body del elemento receta que se quiere agregar
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la receta agregada completa en caso de que todo vaya bien.
	 */
	//agregar validaciones con @Valid y BindingResult result
	@PostMapping("/add")
	public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe){
		
		Recipe newRecipe = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			newRecipe = recipeService.save(recipe);
		}catch(DataAccessException e) {
			responseError.put("mensaje", "Error al intentar insertar en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		RecipeDTOIngredientListless recipeIngredientListless = new RecipeDTOIngredientListless(newRecipe);
//		ResponseRecipeDTOIngredientListless responseRecipe = new ResponseRecipeDTOIngredientListless(recipeIngredientListless);
		
		ResponseRecipeDTOIngredientListless responseRecipe = 
				new ResponseRecipeDTOIngredientListless(
						new RecipeDTOIngredientListless(newRecipe));
		
		return new ResponseEntity<ResponseRecipeDTOIngredientListless>(responseRecipe,HttpStatus.CREATED);
	}
	
	//revisar si funciona bien la actualización con el nuevo constructor	
	/**
	 * 
	 * Actualiza una receta existente en la base de datos.
	 * 
	 * @param recipe recipe body con los nuevos datos.
	 * @param id id asociado como clave primaria a la receta que se quiere actualizar
	 * 
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la receta actualizada completa en caso de que todo vaya bien.
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe, @PathVariable Integer id){

		Recipe recipeOriginal = null;
		Recipe recipeUpdated = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			recipeOriginal = recipeService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al recuperar la receta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(recipeOriginal==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		try {
			recipeOriginal = new Recipe(id, recipe);
//			recipeOriginal.setRecipeName(recipe.getRecipeName());
//			recipeOriginal.setDifficulty(recipe.getDifficulty());
//			recipeOriginal.setTimeRequired(recipe.getTimeRequired());
//			recipeOriginal.setDescription(recipe.getDescription());
//			recipeOriginal.setInstructions(recipe.getInstructions());
//			recipeOriginal.setIngredients(recipe.getIngredients());
//			recipeOriginal.setImage(recipe.getImage());
			recipeUpdated = recipeService.save(recipeOriginal);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al actualizar la receta en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		RecipeDTOIngredientListless recipeIngredientListless = new RecipeDTOIngredientListless(recipeUpdated);
//		ResponseRecipeDTOIngredientListless responseRecipe = new ResponseRecipeDTOIngredientListless(recipeIngredientListless);
		
		ResponseRecipeDTOIngredientListless responseRecipe = 
				new ResponseRecipeDTOIngredientListless(
						new RecipeDTOIngredientListless(recipeUpdated));


		return new ResponseEntity<ResponseRecipeDTOIngredientListless>(responseRecipe, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Borra una receta existente en la base de datos
	 * 
	 * @param id id asociado como clave primaria a la receta que se quiere borrar
	 * @return ResponseEntity con mensaje indicando si ha ocurrido algún error 
	 * o se ha realizado el borrado correctamente.
	 */
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
