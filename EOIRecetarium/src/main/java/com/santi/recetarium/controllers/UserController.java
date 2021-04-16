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

import com.santi.recetarium.models.entities.User;
import com.santi.recetarium.models.entities.dto.UserDTOIngredientListless;
import com.santi.recetarium.models.entities.dto.UserDTOPublic;
import com.santi.recetarium.models.entities.responses.ResponseUserDTOIngredientListless;
import com.santi.recetarium.models.entities.responses.ResponseUserDTOPublic;
import com.santi.recetarium.models.entities.responses.ResponseUsersDTOIngredientListless;
import com.santi.recetarium.models.entities.responses.ResponseUsersDTOPublic;
import com.santi.recetarium.models.services.IUserServiceIMPL;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
//agregar validaciones con @Valid y BindingResult result
	@Autowired
	private IUserServiceIMPL userService;
	
	/**
	 * Recupera todos los usuarios completos.
	 * Para usar solo por admin y sistema.
	 * 
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la respuesta conteniendo la lista de usuarios recuperados en caso de que todo vaya bien.
	 */
	@GetMapping("/all/private")
	public ResponseEntity<?> getAllUsers(){
		
		List<User> users = new ArrayList<User>();
		Map<String, Object> responseError = new HashMap();
		
		try {
			users = userService.findAll();
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(users.size()==0) {
			responseError.put("mensaje", "No existen usuarios");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		List<UserDTOIngredientListless> usersListless = new ArrayList<>();
		users.forEach(u->usersListless.add(new UserDTOIngredientListless(u)));
		ResponseUsersDTOIngredientListless responseUsers = new ResponseUsersDTOIngredientListless(usersListless);
		
		return new ResponseEntity<ResponseUsersDTOIngredientListless>(responseUsers, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Recupera perfil público sin contraseña de los usuarios para mostrar sus datos de manera segura.
	 * Contiene solo id, nickname, email y recetas.
	 * 
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con la lista de perfiles públicos recuperadas en caso de que todo vaya bien.
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getUsersPublicProfile(){
		
		List<User> users = new ArrayList<User>();
		Map<String, Object> responseError = new HashMap();
		
		try {
			users = userService.findAll();
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(users.size()==0) {
			responseError.put("mensaje", "No existen ingredientes");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		List<UserDTOPublic> usersPublic = new ArrayList<UserDTOPublic>();
		users.forEach(r-> usersPublic.add(new UserDTOPublic(r)));
		ResponseUsersDTOPublic responseUsers = new ResponseUsersDTOPublic(usersPublic);
		
		return new ResponseEntity<ResponseUsersDTOPublic>(responseUsers, HttpStatus.OK);
	}
	
//hay que implementar otro find para que los usuarios puedan encontrarse por nickname
	/**
	 * 
	 * Recupera un usuario a partir de su identificador, mostrando todos sus atributos.
	 * Para usar solo por admin y sistema.
	 * 
	 * @param id id asociado como clave primaria al usuario que se quiere recuperar
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con el usuario público recuperado en caso de que todo vaya bien.
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id){

		User user = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			user = userService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al realizar la consulta de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(user==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		ResponseUserDTOIngredientListless responseUser = 
				new ResponseUserDTOIngredientListless(
						new UserDTOIngredientListless(user));
		
		return new ResponseEntity<ResponseUserDTOIngredientListless>(responseUser, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Agrega un usuario nuevo a la base de datos a partir de sus atributos sin id.
	 * 
	 * @param user user body del usuario que se quiere agregar
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con el usuario agregado en caso de que todo vaya bien.
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody User user){
		
		User newUser = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			newUser = userService.save(user);
		}catch(DataAccessException e) {
			responseError.put("mensaje", "Error al intentar insertar en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseUserDTOPublic responseUser = 
				new ResponseUserDTOPublic(
						new UserDTOPublic(newUser));
		
		return new ResponseEntity<ResponseUserDTOPublic>(responseUser,HttpStatus.CREATED);
	}
//revisar o implementar para poder cambiar la contraseña y que salga un mensaje sin mostrarla	
	/**
	 * 
	 * Actualiza un usuario existente en la base de datos.
	 * 
	 * @param id id asociado como clave primaria al usuario que se quiere actualizar
 	 * @param user user body con los nuevos datos.
 	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con el usuario actualizado en caso de que todo vaya bien.
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user){

		User userOriginal = null;
		User userUpdated = null;
		Map<String, Object> responseError = new HashMap();
		
		try {
			userOriginal = userService.findById(id);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al recuperar el usuario de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(userOriginal==null) {
			responseError.put("mensaje", "El identificador buscado no existe");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_FOUND);
		}
		
		try {
			userOriginal = new User(id, user);
			userUpdated = userService.save(userOriginal);
		}catch (DataAccessException e) { 
			responseError.put("mensaje", "Error al actualizar la receta en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseUserDTOPublic responseUser = 
				new ResponseUserDTOPublic(
						new UserDTOPublic(userUpdated));

		return new ResponseEntity<ResponseUserDTOPublic>(responseUser, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Borra un usuario existente en la base de datos a apartir de su identificador.
	 * 
	 * @param id id asociado como clave primaria al usuario que se quiere borrar
	 * @return ResponseEntity con mensaje indicando si ha ocurrido algún error 
	 * o se ha realizado el borrado correctamente.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		
		Map<String, Object> responseError = new HashMap();
		
		try {
			userService.deleteById(id);
		}catch (DataAccessException e) {
			responseError.put("mensaje", "Error al intentar borrar de la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		responseError.put("mensaje","El usuario con id " + id + " ha sido borrado con éxito");

		return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NO_CONTENT);
	}
}
