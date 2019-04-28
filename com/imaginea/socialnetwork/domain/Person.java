package com.imaginea.socialnetwork.domain;

import com.imaginea.socialnetwork.service.PostingService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.imaginea.socialnetwork.data.PredicateContainer;
import com.imaginea.socialnetwork.data.UsersRepository;
import com.imaginea.socialnetwork.registration.Registration;
import com.imaginea.socialnetwork.service.FriendsService;

public class Person {

	private PersonalInfo personalInfo;
	private Address address;
	private List<String> interests;
	private List<String> hobbies;
	private List<AcadamicInfo> acadamicDetails;
	private List<PersonWorkExperience> workDetails;
	private List<Person> friends;
	private List<String> posts;

	public Person(Registration registration) {
		this.personalInfo = registration.getPersonalInfo();
	}

	public Person() {
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public String uniqueIdentifier() {
		return personalInfo.getEmail();
	}
	public boolean isUserNameMatchingTo(final String username) {
		return this.personalInfo.isUserNameMatchingTo(username);
	}
public void profile(String username)
{
	Optional<Person> person = UsersRepository.getInstance().retrievePersonBasedOnUsername(username);
	System.out.println(person.get().toString());
	
}
	@Override
	public String toString() {
		return "Person [personalInfo=" + personalInfo + ", address=" + address + ", interests=" + interests
				+ ", hobbies=" + hobbies + ", acadamicDetails=" + acadamicDetails + ", workDetails=" + workDetails
				+ ", friends=" + friends + "]";
	}

}
