package com.imaginea.socialnetwork.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.imaginea.socialnetwork.data.AuthenticationData;
import com.imaginea.socialnetwork.data.UsersRepository;
import com.imaginea.socialnetwork.domain.Gender;
import com.imaginea.socialnetwork.domain.Person;
import com.imaginea.socialnetwork.domain.PersonalInfo;
import com.imaginea.socialnetwork.registration.Registration;
import com.imaginea.socialnetwork.service.ValidationService;
public class RegistrationService {
	static Scanner scanner=new Scanner(System.in);
	private static ValidationService validationService = new ValidationService();
	public  void processRegistration() {
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
	private static void register(Registration registration, String username, String password) {

		List<String> errors =  validationService.validate(registration);

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
}
