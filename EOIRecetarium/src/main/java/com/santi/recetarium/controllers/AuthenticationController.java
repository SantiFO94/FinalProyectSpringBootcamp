package com.santi.recetarium.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.recetarium.models.entities.User;
import com.santi.recetarium.models.entities.dto.UserDTOLogin;
import com.santi.recetarium.models.entities.dto.UserDTORegister;
import com.santi.recetarium.models.entities.responses.ResponseLogin;
import com.santi.recetarium.models.services.IUserServiceIMPL;
import com.santi.recetarium.security.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private IUserServiceIMPL usersService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseLogin> login(@RequestBody UserDTOLogin userLogin) throws NoSuchAlgorithmException {	
		User user = usersService.login(userLogin.getNickname(), userLogin.getPassword());
		
		if(user != null) {
			return ResponseEntity.ok().body(new ResponseLogin(getToken(user)));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	/**
	 * Agrega un usuario nuevo a la base de datos a partir de sus atributos sin id.
	 * 
	 * @param user user body del usuario que se quiere agregar
	 * @return ResponseEntity con mensaje de error en caso de que ocurra algún problema
	 * o con el usuario agregado en caso de que todo vaya bien.
	 */
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTORegister userRegister) throws NoSuchAlgorithmException {
		
		Map<String, Object> responseError = new HashMap();

		if(null== userRegister) {
			responseError.put("mensaje", "No se ha recibido la información.");
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			usersService.register(userRegister);
		}catch(DataAccessException e) {
			responseError.put("mensaje", "Error al intentar insertar en la base de datos");
			responseError.put("error", e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null); 
	}
	
	/**
	 * Comprobar si el Token es válido o no. El token tiene que aceptarlo asi
	 *  que al probarlo aqui tiene que devolver código 200 ok.
	 *  @return error en caso de que el token no sea valido.
	 */
	@GetMapping("/validate")
	public void validateToken() {}
	
	/**
	 * Creación de token privado para el usuario
	 * @param user
	 * @return token con la informaciónd e acceso del usuario cifrada
	 */
	private String getToken(User user) {	
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", user.getIdUser());
		data.put("email", user.getEmail());
		data.put("authorities", Arrays.asList("ROLE_USER"));
		
		String token = Jwts.builder().setId("springEventos")
				.setSubject(user.getNickname()).addClaims(data)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				//Algoritmo HS512 el más seguro en cunato a cifrado simétrico
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();
		
		return token;
	}
}
