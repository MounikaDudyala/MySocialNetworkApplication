package com.imaginea.socialnetwork.service;

import java.util.Optional;

import com.imaginea.socialnetwork.data.UsersRepository;
import com.imaginea.socialnetwork.domain.Person;

public class ProfileService {
	public void profile(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		
		Optional<Person> person = UsersRepository.getInstance().retrievePersonBasedOnUsername(username);
		       person.get().profile(username); 
		}

	}
