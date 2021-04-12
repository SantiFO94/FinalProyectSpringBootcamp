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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.recetarium.entity.Ingredient;
import com.santi.recetarium.entity.dto.IngredientDTOCard;
import com.santi.recetarium.entity.response.ResponseIngredient;
import com.santi.recetarium.entity.response.ResponseIngredients;
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
		Map<String, Object> respuesta = new HashMap();
		try {
			ingredients = ingredientService.findAll();
		}catch (DataAccessException e) { 
			respuesta.put("mensaje", "Error al realizar la consulta de la base de datos");
			respuesta.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredients.size()==0) {
			respuesta.put("mensaje", "No existen ingredientes");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		ResponseIngredients response = new ResponseIngredients(ingredients);
		
		return new ResponseEntity<ResponseIngredients>(response, HttpStatus.OK);
	}
	
	//sacar nombres de ingredientes para presentar en la versión resumida
	@GetMapping("/allnames")
	public ResponseEntity<?> getIngredientsNames(){
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Map<String, Object> respuesta = new HashMap();
		try {
			ingredients = ingredientService.findAll();
		}catch (DataAccessException e) { 
			respuesta.put("mensaje", "Error al realizar la consulta de la base de datos");
			respuesta.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredients.size()==0) {
			respuesta.put("mensaje", "No existen ingredientes");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		List<IngredientDTOCard> ingredientsCard = new ArrayList<IngredientDTOCard>();
		ingredients.forEach(i-> ingredientsCard.add(new IngredientDTOCard(i)));
		ResponseIngredients response = new ResponseIngredients(ingredients);
		
		return new ResponseEntity<ResponseIngredients>(response, HttpStatus.OK);
	}
	
	//encontrar un ingrediente completo en la receta (cuando se menciona para ver las cantidades) 
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getIngredient(@PathVariable Long id){

		Ingredient ingredient = null;
		Map<String, Object> respuesta = new HashMap();
		try {
			ingredient = ingredientService.findById(id);
		}catch (DataAccessException e) { 
			respuesta.put("mensaje", "Error al realizar la consulta de la base de datos");
			respuesta.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredient==null) {
			respuesta.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}
	
	//agregar validaciones con @Valid y BindingResult result
	@PostMapping("/add")
	public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient){
		
		Ingredient newIngredient = null;
		Map<String, Object> respuesta = new HashMap();
		
		try {
			newIngredient = ingredientService.save(ingredient);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "Error al intentar insertar en la base de datos");
			respuesta.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseIngredient response = new ResponseIngredient(newIngredient);

		return new ResponseEntity<ResponseIngredient>(response,HttpStatus.CREATED);
	}
	
	//terminar
	@GetMapping("/update/{id}")
	public ResponseEntity<?> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable Long id){

		Ingredient ingredientOriginal = ingredientService.findById(id);
		Ingredient ingredientUpdated = null;
		Map<String, Object> respuesta = new HashMap();
		
		try {
			
		}catch (DataAccessException e) { 
			respuesta.put("mensaje", "Error al realizar la consulta de la base de datos");
			respuesta.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(ingredient==null) {
			respuesta.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id){
		
		Map<String, Object> respuesta = new HashMap();
		
		try {
			ingredientService.deleteById(id);
		}catch (DataAccessException e) { //Base de datos inaccesible
			respuesta.put("mensaje", "Error al intentar borrar de la base de datos");
			respuesta.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje","El ingrediente con id " + id + " ha sido borrado con éxito");

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
