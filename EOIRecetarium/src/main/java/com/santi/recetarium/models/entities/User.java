package com.santi.recetarium.models.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "nickname"))
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_user", unique = true, nullable = false)
	private int idUser;
	@Column(name = "nickname", unique = true, nullable = false, length = 100)
	private String nickname;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "email", length = 100)
	private String email;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Recipe> recipes = new HashSet<Recipe>(0);

	public User() {
	}

	/**
	 * Constructor para actualizar un usuario existente con uno nuevo
	 * 
	 * @param idUser identificador del usuario que se quiere actualizar
	 * @param user body que contiene los nuevos datos
	 */
	public User(int idUser, User user) {
		this.idUser = idUser;
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.recipes = user.getRecipes();
	}

	public User(int idUser, String nickname, String password, String email, Set<Recipe> recipeses) {
		this.idUser = idUser;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.recipes = recipeses;
	}

	
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Recipe> getRecipes() {
		return this.recipes;
	}

	public void setRecipeses(Set<Recipe> recipeses) {
		this.recipes = recipeses;
	}

}
