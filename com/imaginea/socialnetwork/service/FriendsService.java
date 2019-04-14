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
	private static Map<String, String>FriendRequestsMap = new ConcurrentHashMap<>();
	private static Map<String, List<String>> SentRequestsMap = new ConcurrentHashMap<>();
	private static Map<String, List<String>> friendsListMap = new ConcurrentHashMap<>();
	List<String> friendsListOfUser = new ArrayList<>();
	List<String> friendsListOfFriend= new ArrayList<>();

	public Optional<List<String>> namesOfTheFriendsOf(final String username) {

		if(username==null)
			throw new RuntimeException("give username");

		Optional<Person> personOptionalWrapper = UsersRepository.getInstance().retrievePersonBasedOnUsername(username);

		if (!personOptionalWrapper.isPresent())
			return Optional.empty();

		Person person = personOptionalWrapper.get();
		return Optional.ofNullable(person.namesOfTheFriends());

	}

	public Optional<List<Person>> friends(final String username) {

		if(username==null)
			throw new RuntimeException("give username");
		Optional<Person> personOptionalWrapper = UsersRepository.getInstance().retrievePersonBasedOnUsername(username);

		if (!personOptionalWrapper.isPresent())
			return Optional.empty();

		Person person = personOptionalWrapper.get();
		return Optional.of(person.friends());

	}

	public void addfriend(String username) {
		if(username==null)
			throw new RuntimeException("give username");
		System.out.println("whom you wants to add as a friend ");
		usersList();
		do {
			System.out.println("give friends name");
			String friendsName = scanner.next();
			int count = 0;
			if (friendsName == null)
				throw new RuntimeException("give valid friend name");
			List<Person> personlist = UsersRepository.getInstance().ListofUsers();
			for (Person person : personlist) {
				if (friendsName.equals(person.getPersonalInfo().getUsername())) {
					count++;
					friendsListOfUser.add(friendsName);
					System.out.println("request succesfully sent");
					FriendRequestsMap.put(friendsName, username);
                    
				}

			}
			if (count == 0)
				System.out.println("user not exists");
			System.out.println("do u want to add another friend(Y/N)");

		} while (scanner.next().equals("Y"));
		
		SentRequestsMap.put(username, friendsListOfUser);

	}
    
	public List<String> retrivefriends(String username) {
       
		return friendsListMap.get(username);
		 
	}
	public List<String> sendRequests(String username)
	{
		return SentRequestsMap.get(username);
	}
  public void accept(String username)
  {
	  String friend=FriendRequestsMap.get(username);
	     System.out.println("do you want to accept"+friend+"(Y/N)");
	       String answer=scanner.next();
	       if(answer.equals("Y"))
	       {   friendsListOfUser.add(friend);
	    	   friendsListMap.put(username,friendsListOfUser );
	    	   friendsListOfFriend.add(username);
	       friendsListMap.put(friend,friendsListOfFriend);
	        System.out.println("you are friends now");
	       }
	       else
	    	System.out.println("rejected");   
  }
	private static void usersList() {
		List<Person> personlist = UsersRepository.getInstance().ListofUsers();
		for (Person person : personlist) {
			System.out.println(person.getPersonalInfo().getUsername());
		}

	}
	public static FriendsService getInstance() {
		return INSTANCE;
	}
}
