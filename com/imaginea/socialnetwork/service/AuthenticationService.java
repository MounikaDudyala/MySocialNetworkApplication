package com.imaginea.socialnetwork.service;
import com.imaginea.socialnetwork.data.AuthenticationData;

public class AuthenticationService {

	public boolean authenticate(String username, String password) {
		return AuthenticationData.INSTANCE.isUserNamePasswordMatching(username, password);
	}

	public boolean logout(String username) {
		// Process logout
		return true;
	}
}


