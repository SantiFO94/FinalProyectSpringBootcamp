package com.santi.recetarium.models.entities.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.santi.recetarium.models.entities.User;

public class UserDTOIngredientListless implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idUser;
	private String nickname;
	private String password;
	private String email;
	private Set<RecipeDTOIngredientListless> recipes = new HashSet<RecipeDTOIngredientListless>(0);
	
	public UserDTOIngredientListless(User user) {
		this.idUser = user.getIdUser();
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.email = user.getEmail();
		user.getRecipes().forEach(r->this.recipes
				.add(new RecipeDTOIngredientListless(r)));
	}

	public int getIdUser() {
		return idUser;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Set<RecipeDTOIngredientListless> getRecipes() {
		return recipes;
	}
	
	
}
