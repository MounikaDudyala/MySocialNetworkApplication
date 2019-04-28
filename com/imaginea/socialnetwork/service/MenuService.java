package com.imaginea.socialnetwork.service;

import java.util.List;
import java.util.Scanner;

import com.imaginea.socialnetwork.data.UsersRepository;
import com.imaginea.socialnetwork.domain.Person;

public class MenuService {
	static Scanner scanner = new Scanner(System.in);
	static LogoutService logoutService = new LogoutService();
	static ProfileService profileService = new ProfileService();

	public void printMenu() {
		System.out.println("1.Home\n2.Post\n3.Sent Requests\n4.Friend Requests/Notifications\n5.Profile\n"
				+ "6.List Of Users\n7.Posts of me\n8.add friend\n9.Friends List\n"
				+ "10.Friends Profile\n11.MutualFriends\n12.Logout");
	}

	void processMenu(String username) {
		if (username == null)
			throw new RuntimeException("give username");
		System.out.println("give your choice");
		switch (scanner.nextInt()) {

		case 1:
			landOnHome(username);
			// posts of friends
			break;
		case 2:
			addPost(username);
			break;
		case 3:
			sentRequests(username);
			break;
		case 4:
			acceptOrRejectARequest(username);
			break;
		case 5:
			profile(username);
			break;
		case 6:
			usersList();
			break;
		case 7:
			postsOfMe(username);
			break;
		case 8:
			addFriends(username);
			break;
		case 9:
			friendsOfMe(username);
			break;
		case 10:
			friendsProfile(username);
			break;
		case 11:
			mutualFriends(username);
			break;
		case 12:
			logout(username);
			break;

		}
		processMenu(username);
	}

	private static void landOnHome(String username) {

		FeedService.getInstance().postsOfFriends(username);

	}

	private static void addPost(String username) {

		PostingService.getInstance().addPost(username);
	}

	private static void sentRequests(String username) {

		FriendsService.getInstance().sentRequests(username);

	}

	private static void acceptOrRejectARequest(String username) {

		FriendsService.getInstance().acceptOrReject(username);
	}

	private static void friendsProfile(String username) {
		FriendsService.getInstance().friendsProfile(username);
	}

	private static void mutualFriends(String username) {

		FriendsService.getInstance().MutualFriends(username);
	}

	private static void postsOfMe(String username) {

		PostingService.getInstance().printPosts(username);

	}

	public void friendsOfMe(String username) {

		FriendsService.getInstance().printFriendsNames(username);

	}

	public void addFriends(String username) {

		FriendsService.getInstance().addfriend(username);
	}

	private static void usersList() {
		List<Person> personlist = UsersRepository.getInstance().ListofUsers();
		for (Person person : personlist) {
			System.out.println(person.getPersonalInfo().name());
		}
	}

	private static void profile(String username) {
		profileService.profile(username);
	}

	private static void logout(final String username) {
		logoutService.logout(username);
	}
}
