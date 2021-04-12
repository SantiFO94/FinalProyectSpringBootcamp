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
	
	@GetMapping("/all")
	public ResponseEntity<ResponseIngredients> getIngredients(){
		
		List<Ingredient> ingredients = ingredientService.findAll();
		List<IngredientDTOCard> ingredientsDTO = new ArrayList<>();		
		ingredients.stream().forEach(i -> ingredientsDTO.add(new IngredientDTOCard(i)));
		
		ResponseIngredients response = new ResponseIngredients(ingredientsDTO);
		
		return new ResponseEntity<ResponseIngredients>(response, HttpStatus.OK);
	}
	
	@GetMapping("/ingredients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){

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
	
	@PostMapping("/add")
	public ResponseEntity<ResponseIngredient> addTarea(@RequestBody Ingredient ingredient){
		
		Ingredient newIngredient = ingredientService.save(ingredient);
		
		IngredientDTOCard ingredientDTO = new IngredientDTOCard(newIngredient);
		
		ResponseIngredient response = new ResponseIngredient(ingredientDTO);
		
		return new ResponseEntity<ResponseIngredient>(response,HttpStatus.CREATED);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id){
		
		ingredientService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
