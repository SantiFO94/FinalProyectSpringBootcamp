package com.santi.recetarium.models.entities.dto;

import com.santi.recetarium.models.entities.User;

public class UserDTOPublic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idUser;
	private String nickname;
	private String email;
	
	public UserDTOPublic(User user) {
		this.idUser = user.getIdUser();
		this.nickname = user.getNickname();
		this.email = user.getEmail();

	}

	public int getIdUser() {
		return idUser;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

}
