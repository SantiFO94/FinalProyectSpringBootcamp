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

import com.santi.recetarium.models.entity.Ingredients;
import com.santi.recetarium.models.entity.dto.IngredientDTOCard;
import com.santi.recetarium.models.entity.dto.IngredientDTOListless;
import com.santi.recetarium.models.entity.response.ResponseIngredientsDTOCard;
import com.santi.recetarium.models.entity.response.ResponseIngredientsDTOListless;
import com.santi.recetarium.models.services.IIngredientServiceIMPL;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
//Aqui los metodos que devuelven objeto individual estan con DTO directamente
	@Autowired
	private IIngredientServiceIMPL ingredientService;
	
	//sacar todos los ingredientes completos en la receta
	@GetMapping("/all")
	public ResponseEntity<?> getAllIngredients(){
		
		List<Ingredients> ingredients = new ArrayList<Ingredients>();
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
		
		List<IngredientDTOListless> ingredientsListless = new ArrayList<IngredientDTOListless>();
		ingredients.forEach(i-> ingredientsListless.add(new IngredientDTOListless(i)));
		ResponseIngredientsDTOListless responseIngredient = new ResponseIngredientsDTOListless(ingredientsListless);
		
		return new ResponseEntity<ResponseIngredientsDTOListless>(responseIngredient, HttpStatus.OK);
	}
	
	//sacar nombres de ingredientes para presentar en la versión resumida
	@GetMapping("/all/names")
	public ResponseEntity<?> getIngredientsNames(){
		
		List<Ingredients> ingredients = new ArrayList<Ingredients>();
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
	public ResponseEntity<?> getIngredient(@PathVariable Integer id){

		Ingredients ingredient = null;
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
		
		IngredientDTOListless responseIngredient = new IngredientDTOListless(ingredient);
		
		return new ResponseEntity<IngredientDTOListless>(responseIngredient, HttpStatus.OK);
	}
	
	//agregar validaciones con @Valid y BindingResult result
	@PostMapping("/add")
	public ResponseEntity<?> addIngredient(@RequestBody Ingredients ingredient){
		
		Ingredients newIngredient = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			newIngredient = ingredientService.save(ingredient);
		}catch(DataAccessException e) {
			responseError.put("mensaje", "Error al intentar insertar en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		IngredientDTOListless responseIngredient = new IngredientDTOListless(newIngredient);

		return new ResponseEntity<IngredientDTOListless>(responseIngredient,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateIngredient(@RequestBody Ingredients ingredient, @PathVariable Integer id){

		Ingredients ingredientOriginal = null;
		Ingredients ingredientUpdated = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredientOriginal = ingredientService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al recuperar el ingrediente de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ingredientOriginal==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		try {
			ingredientOriginal.setName(ingredient.getName());
//			ingredientOriginal.setQuantity(ingredient.getQuantity());
//			ingredientOriginal.setMeasure(ingredient.getMeasure());
			ingredientUpdated = ingredientService.save(ingredientOriginal);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al actualizar el ingrediente en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		IngredientDTOListless responseIngredient = new IngredientDTOListless(ingredientUpdated);
		
		return new ResponseEntity<IngredientDTOListless>(responseIngredient, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Integer id){
		
		Map<String, Object> responseError = new HashMap();
		
		try {
			ingredientService.deleteById(id);
		}catch (DataAccessException e) {
			responseError.put("mensaje", "Error al intentar borrar de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseError.put("mensaje","El ingrediente con id " + id + " ha sido borrado con éxito");

		return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NO_CONTENT);
	}
}
