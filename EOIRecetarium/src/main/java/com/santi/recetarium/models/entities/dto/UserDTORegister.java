package com.santi.recetarium.models.entities.dto;

import java.io.Serializable;

import com.santi.recetarium.models.entities.User;

public class UserDTORegister  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nickname;
	private String password;
	private String email;
	
	public UserDTORegister(User user) {
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.email= user.getEmail();
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return null;
	}
	
	
}
