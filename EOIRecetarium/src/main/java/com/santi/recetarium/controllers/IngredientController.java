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

import com.santi.recetarium.entity.Ingredient;
import com.santi.recetarium.entity.dto.IngredientDTOCard;
import com.santi.recetarium.entity.response.ResponseIngredient;
import com.santi.recetarium.entity.response.ResponseIngredients;
import com.santi.recetarium.entity.response.ResponseIngredientsDTOCard;
import com.santi.recetarium.services.IIngredientServiceIMPL;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IIngredientServiceIMPL ingredientService;
	
	//sacar todos los ingredientes completos en la receta
	@GetMapping("/all")
	public ResponseEntity<?> getAllIngredients(){
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredients = ingredientService.findAll();
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredients.size()==0) {
			responseError.put("mensaje", "No existen ingredientes");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		ResponseIngredients responseIngredient = new ResponseIngredients(ingredients);
		
		return new ResponseEntity<ResponseIngredients>(responseIngredient, HttpStatus.OK);
	}
	
	//sacar nombres de ingredientes para presentar en la versión resumida
	@GetMapping("/allnames")
	public ResponseEntity<?> getIngredientsNames(){
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredients = ingredientService.findAll();
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredients.size()==0) {
			responseError.put("mensaje", "No existen ingredientes");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		List<IngredientDTOCard> ingredientsCard = new ArrayList<IngredientDTOCard>();
		ingredients.forEach(i-> ingredientsCard.add(new IngredientDTOCard(i)));
		ResponseIngredientsDTOCard responseIngredient = new ResponseIngredientsDTOCard(ingredientsCard);
		
		return new ResponseEntity<ResponseIngredientsDTOCard>(responseIngredient, HttpStatus.OK);
	}
	
	//encontrar un ingrediente completo en la receta (cuando se menciona para ver las cantidades) 
	@GetMapping("/ingredient/{id}")
	public ResponseEntity<?> getIngredient(@PathVariable Long id){

		Ingredient ingredient = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredient = ingredientService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredient==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		ResponseIngredient responseIngredient = new ResponseIngredient(ingredient);
		
		return new ResponseEntity<ResponseIngredient>(responseIngredient, HttpStatus.OK);
	}
	
	//agregar validaciones con @Valid y BindingResult result
	@PostMapping("/add")
	public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient){
		
		Ingredient newIngredient = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			newIngredient = ingredientService.save(ingredient);
		}catch(DataAccessException e) {
			responseError.put("mensaje", "Error al intentar insertar en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseIngredient responseIngredient = new ResponseIngredient(newIngredient);

		return new ResponseEntity<ResponseIngredient>(responseIngredient,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable Long id){

		Ingredient ingredientOriginal = null;
		Ingredient ingredientUpdated = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredientOriginal = ingredientService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al recuperar el ingrediente de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ingredient==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		try {
			ingredientOriginal.setName(ingredient.getName());
			ingredientOriginal.setQuantity(ingredient.getQuantity());
			ingredientOriginal.setMeasure(ingredient.getMeasure());
			ingredientUpdated = ingredientService.save(ingredientOriginal);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al actualizar el ingrediente en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseIngredient responseIngredient = new ResponseIngredient(ingredientUpdated);
		
		return new ResponseEntity<ResponseIngredient>(responseIngredient, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id){
		
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredientService.deleteById(id);
		}catch (DataAccessException e) {
			responseError.put("mensaje", "Error al intentar borrar de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseError.put("mensaje","El ingrediente con id " + id + " ha sido borrado con éxito");

		return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.OK);
	}
}
