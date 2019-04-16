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
	Scanner scanner = new Scanner(System.in);

	public void addPost(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		PostingService.getInstance().addPost(username);
	}

	public void retrivePosts(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		PostingService.getInstance().retrivePosts(username);

	}

	public void addfriends(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		System.out.println("give friends name");
		String friendName = scanner.next();
		FriendsService.getInstance().addfriend(username,friendName);
	}

	public void retriveFriends(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		FriendsService.getInstance().retriveFriends(username);

	}

	public void PostsOfFriends(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		PostingService.getInstance().postsOfFriends(username);

	}

	public void sentRequests(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		FriendsService.getInstance().sentRequests(username);

	}

	public void acceptOrReject(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		FriendsService.getInstance().acceptOrReject(username);
	}

	public void profile(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		Optional<Person> person = UsersRepository.getInstance().retrievePersonBasedOnUsername(username);
		System.out.println(person.get().personalInfo.toString());
		System.out.println(person.get().friends);
		System.out.println(person.get().interests);
		System.out.println(person.get().hobbies);
		System.out.println(person.get().acadamicDetails);
		System.out.println(person.get().workDetails);

	}

	public void friendsProfile(String username) {
		FriendsService.getInstance().friendsProfile(username);
	}

	public void mutualFriends(String username) {
		System.out.println("give friendName");
		String friendName = scanner.next();
		FriendsService.getInstance().MutualFriends(username, friendName);
	}

	public String uniqueIdentifier() {
		return personalInfo.getEmail();
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
