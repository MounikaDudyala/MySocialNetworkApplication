package com.imaginea.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.socialnetwork.data.AuthenticationData;
import com.imaginea.socialnetwork.registration.Registration;

public class ValidationService {
	public  List<String> validate(Registration registration) {
		List<String> errors = new ArrayList<>();
		if (isUsernameAlreadyExists(registration.getPersonalInfo().getUsername()))
			errors.add("Username already exists");

		return errors;
	}

	private static boolean isUsernameAlreadyExists(final String username) {
		return AuthenticationData.INSTANCE.isUserNameAlreadyExists(username);
	}
}
