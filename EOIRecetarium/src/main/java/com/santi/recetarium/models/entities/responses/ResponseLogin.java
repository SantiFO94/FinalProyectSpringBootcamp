package com.santi.recetarium.models.entities.responses;

public class ResponseLogin {

	private String accessToken;

	public ResponseLogin(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
