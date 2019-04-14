
package com.imaginea.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class PostingService {
	private static PostingService INSTANCE = new PostingService();
	private static Map<String, List<String>> usersPostMap = new ConcurrentHashMap<>();
	List<String> messagelist = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);

	public void addPost(String username) {
		if (username == null)
			throw new RuntimeException("give posting message");
		do {
			System.out.println("give your posting message");
			String message = scanner.next();
			if (message == null)
				throw new RuntimeException("give posting message");
			messagelist.add(message);
			System.out.println("do u want to post another message(Y/N)");

		} while (scanner.next().equals("Y"));
		usersPostMap.put(username, messagelist);

	}

	public List<String> retrivePosts(String username) {

		return usersPostMap.get(username);
	}

	public static PostingService getInstance() {
		return INSTANCE;
	}

}
