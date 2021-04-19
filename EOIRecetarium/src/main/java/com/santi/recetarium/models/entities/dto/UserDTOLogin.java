package com.santi.recetarium.models.entities.dto;

import java.io.Serializable;

import com.santi.recetarium.models.entities.User;

public class UserDTOLogin  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nickname;
	private String password;
	
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
