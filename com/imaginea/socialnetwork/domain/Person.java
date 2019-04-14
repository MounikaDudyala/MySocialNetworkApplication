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
	PostingService postService = new PostingService();
	FriendsService friendsService = new FriendsService();
	Scanner scanner = new Scanner(System.in);
	public void addPost(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		PostingService.getInstance().addPost(username);
	}

	public List<String> retrivePosts(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		return PostingService.getInstance().retrivePosts(username);
	}

	public void addfriends(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		FriendsService.getInstance().addfriend(username);
	}

	public List<String> retrivefriends(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		return FriendsService.getInstance().retrivefriends(username);

	}

	public void sendRequests(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		List<String> requestSend = FriendsService.getInstance().sendRequests(username);
		for (String request : requestSend) {
			System.out.println(request);
		}
	}

	public void accept(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		FriendsService.getInstance().accept(username);
	}

	public void profile(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		Optional<Person> person = UsersRepository.getInstance().retrievePersonBasedOnUsername(username);
		System.out.println(person.get().personalInfo.toString());
		System.out.println(person.get().friends);
		System.out.println(person.get().interests);
		System.out.println(person.get().hobbies);
		System.out.println(person.get().acadamicDetails);
		System.out.println(person.get().workDetails);
		

	}

	public String uniqueIdentifier() {
		return personalInfo.getEmail();
	}

	public List<String> namesOfTheFriends() {
		return friends.stream().map(p -> p.personalInfo.name()).collect(Collectors.toList());
	}

	public List<Person> friends() {
		return friends;
	}

	public Person(Registration registration) {
		this.personalInfo = registration.getPersonalInfo();
	}

	public Person() {
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public boolean isUserNameMatchingTo(final String username) {
		return this.personalInfo.isUserNameMatchingTo(username);
	}

	public boolean isEmailEqualTo(String email) {
		if (this.personalInfo == null)
			return false;
		return this.personalInfo.isEmailEqualTo(email);
	}

	@Override
	public String toString() {
		return "Person [personalInfo=" + personalInfo + ", address=" + address + ", interests=" + interests
				+ ", hobbies=" + hobbies + ", acadamicDetails=" + acadamicDetails + ", workDetails=" + workDetails
				+ ", friends=" + friends + "]";
	}

}
