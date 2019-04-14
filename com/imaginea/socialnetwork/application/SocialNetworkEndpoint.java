package com.imaginea.socialnetwork.application;

import com.imaginea.socialnetwork.domain.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.imaginea.socialnetwork.data.AuthenticationData;
import com.imaginea.socialnetwork.data.PredicateContainer;
import com.imaginea.socialnetwork.data.UsersRepository;
import com.imaginea.socialnetwork.domain.Gender;
import com.imaginea.socialnetwork.domain.PersonalInfo;
import com.imaginea.socialnetwork.registration.Registration;
import com.imaginea.socialnetwork.service.AuthenticationService;


public class SocialNetworkEndpoint {

	static Scanner scanner = new Scanner(System.in);
	static Person person = new Person();
	private static AuthenticationService authenticationService = new AuthenticationService();

	public static void main(String[] args) {

		while (true) {
			// Show options to the user
			entryMessage();
			processEntryInput();

		}

	}

	private static void entryMessage() {
		System.out.println("Please chose from following options");
		System.out.println("1. Login \n2. Registration");
	}

	private static void processEntryInput() {
		int input = scanner.nextInt();
		switch (input) {
		case 1:
			processLogin();
			break;
		case 2:
			processRegistration();

		}
	}

	private static void processRegistration() {
		// Registration
		System.out.println("Registration process is initiated. Please provide below details");
		System.out.println("Phone:");
		String phone = scanner.next();
		System.out.println("E-Mail:");
		String email = scanner.next();
		System.out.println("firstName:");
		String firstName = scanner.next();
		System.out.println("lastName:");
		String lastName = scanner.next();
		System.out.println("Gender M/F:");
		String userGenderInput = scanner.next();

		Gender gender = Gender.MALE;
		if (userGenderInput.equals("F"))
			gender = Gender.FEMALE;

		System.out.println("Date of Birth (dd/mm/YYYY):");
		String dob = scanner.next();

		System.out.println("Username:");
		String username = scanner.next();

		System.out.println("password:");
		String password = scanner.next();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date dateOfBirth = null;
		try {
			dateOfBirth = dateFormat.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		PersonalInfo personalInfo = new PersonalInfo(phone, email, username, firstName, lastName, gender, dateOfBirth);
		Registration registration = new Registration(personalInfo, password);
		register(registration, username, password);
	}

	private static void processLogin() {
		// Login
		System.out.println("Please enter your username and password");
		String username = scanner.next();
		String password = scanner.next();

		if (authenticationService.authenticate(username, password)) {
			if (username == null)
				throw new RuntimeException("Empty username - Can not find user.");
			postLogin(username);

		} else
			System.out.println("Login is failed");
	}

	private static void postLogin(final String username) {
		if (username == null)
			throw new RuntimeException("Empty username - Can not find user.");
		System.out.println("Login is successfull");

		System.out.println("Browse through feed  OR chose one of the options from below.");

		System.out.println("1.Home");
		System.out.println("2.Post");
		System.out.println("3.Sent Requests");
		System.out.println("4.Friend Requests/Notifications");
		System.out.println("5.Profile");
		System.out.println("6.List Of Users");
		System.out.println("7.Posts of me");
		System.out.println("8.add friend");
		System.out.println("9.Friends List");
		System.out.println("10.Logout");
		switch (scanner.nextInt()) {

		case 1:
			landOnHome(username);
			//posts of friends
			break;
		case 2:
			person.addPost(username);
			break;
		case 3:
			person.sendRequests(username);
			break;
		case 4:
			person.accept(username);
		
		case 5:
			person.profile(username);
			break;
		case 6:
			usersList();
			break;
		case 7:
			postsOfMe(username);
			break;
		case 8:
			person.addfriends(username);
			  break;
		case 9:
			 friendsOfMe(username);
			  break;
		case 10:
			logout(username);
			break;

		}

	}

	private static void usersList() {
		List<Person> personlist = UsersRepository.getInstance().ListofUsers();
		for (Person person : personlist) {
			System.out.println(person.getPersonalInfo().name());
		}

	}

	public static void landOnHome(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		List<String> ListOfFriends = person.retrivefriends(username);
		for (String list : ListOfFriends) {
			String usernameOffriends = list;
            List<String> postsOfFriends=  person.retrivePosts(usernameOffriends);
            print(postsOfFriends);
		}

	}

	private static void logout(final String username) {
		if (authenticationService.logout(username))
			System.out.println("Logout is successful!");
		else
			System.out.println("Logout is failed!");

	}

	private static void postsOfMe(String username) {
		List<String> postings = person.retrivePosts(username);
		System.out.println("Posts of"+username);
		print(postings);
	}

	private static void friendsOfMe(String username) {
		retriveFriends(username);
		
	}
	private static void print(List<String> strings) {
		System.out.println(strings);
	}
  private static void retriveFriends(String username)
  {
	  List<String> friendsList=person.retrivefriends(username);
	  print(friendsList);
  }
	private static void register(Registration registration, String username, String password) {

		
		List<String> errors = validate(registration);

		if (!errors.isEmpty()) {
			errors.forEach(System.out::println);
			return;
		}
		saveToRepository(registration);
		storeUserCredentials(username, password);
		System.out.println("Account created successfully!!");

	}

	private static void storeUserCredentials(String username, String password) {
		AuthenticationData.INSTANCE.store(username, password);
	}

	private static UsersRepository saveToRepository(Registration registration) {
		UsersRepository usersRepository = UsersRepository.getInstance();
		usersRepository.addUser(new Person(registration));
		return usersRepository;
	}

	private static List<String> validate(Registration registration) {
		List<String> errors = new ArrayList<>();
		if (isUsernameAlreadyExists(registration.getPersonalInfo().getUsername()))
			errors.add("Username already exists");

		if (isEmailAlreadyExists(registration.getPersonalInfo().getEmail()))
			errors.add("Email already exists");

		return errors;
	}

	private static boolean isEmailAlreadyExists(String email) {
		return false;
	}

	private static boolean isUsernameAlreadyExists(final String username) {
		return AuthenticationData.INSTANCE.isUserNameAlreadyExists(username);
	}
}
