package com.imaginea.socialnetwork.registration;

import com.imaginea.socialnetwork.domain.PersonalInfo;

public class Registration {
	private final PersonalInfo personalInfo;
	private final String password;

	public Registration(PersonalInfo personalInfo, String password) {
		super();
		this.personalInfo = personalInfo;
		this.password = password;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

}
