package com.imaginea.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import com.imaginea.socialnetwork.data.PredicateContainer;
import com.imaginea.socialnetwork.data.UsersRepository;
import com.imaginea.socialnetwork.domain.Person;

public class FriendsService {
	private static FriendsService INSTANCE = new FriendsService();
	Scanner scanner = new Scanner(System.in);
	Person person = new Person();
	private static Map<String, String> FriendRequestsMap = new ConcurrentHashMap<>();
	private static Map<String, List<String>> SentRequestsMap = new ConcurrentHashMap<>();
	private static Map<String, List<String>> friendsListMap = new ConcurrentHashMap<>();
	List<String> friendsListOfUser = new ArrayList<>();
	List<String> friendsListOfFriend = new ArrayList<>();

	public void addfriend(String username, String friendName) {
		if (username == null)
			throw new RuntimeException("give username");
		int count = 0;
		if (friendName == null)
			throw new RuntimeException("give valid friend name");
		List<Person> personlist = UsersRepository.getInstance().ListofUsers();
		for (Person person : personlist) {
			if (friendName.equals(person.getPersonalInfo().getUsername())) {
				count++;
				friendsListOfUser.add(friendName);
				System.out.println("request succesfully sent");
				FriendRequestsMap.put(friendName, username);

			}

		}
		if (count == 0)
			System.out.println("user not exists");
		System.out.println("do u want to add another friend(Y/N)");

		SentRequestsMap.put(username, friendsListOfUser);

	}

	public void retriveFriends(String username) {

		List<String> friendsList = friendsListMap.get(username);
		print(friendsList);

	}

	public List<String> namesOffriends(String username) {
		List<String> friendsList = friendsListMap.get(username);
		return friendsList;
	}

	public void friendsProfile(String username) {
		retriveFriends(username);
		System.out.println("whom profile you wants to see");
		String friendName = scanner.next();
		person.profile(friendName);

	}

	public void sentRequests(String username) {

		List<String> requestsSend = SentRequestsMap.get(username);
		print(requestsSend);
	}

	public void acceptOrReject(String username) {
		String friend = FriendRequestsMap.get(username);
		System.out.println("do you want to accept" + friend + "(Y/N)");
		String answer = scanner.next();
		if (answer.equals("Y")) {
			acceptRequest(username);
		} else
			rejectRequest(username);

	}

	public void acceptRequest(String username) {
		String friend = FriendRequestsMap.get(username);
		friendsListOfUser.add(friend);
		friendsListMap.put(username, friendsListOfUser);
		friendsListOfFriend.add(username);
		friendsListMap.put(friend, friendsListOfFriend);
		System.out.println("you are friends now");
	}

	public void rejectRequest(String username) {
		System.out.println("friend request rejected");
	}

	public void MutualFriends(String username, String friendName) {
		List<String> friendslist1 = friendsListMap.get(username);
		List<String> friendslist2 = friendsListMap.get(friendName);
		for (String name1 : friendslist1) {
			for (String name2 : friendslist2) {
				if (name1.equals(name2))
					System.out.println(name1);
			}
		}
	}

	private static void print(List<String> strings) {
		System.out.println(strings);
	}

	public static FriendsService getInstance() {
		return INSTANCE;
	}
}
