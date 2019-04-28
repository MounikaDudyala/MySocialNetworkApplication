package com.imaginea.socialnetwork.service;

import java.util.List;

public class FeedService {

PostingService postingService=new PostingService();
FriendsService friendsService=new FriendsService();
private static FeedService INSTANCE = new FeedService();
public static FeedService getInstance() {
	return INSTANCE;
}
public void postsOfFriends(String username) {
	List<String> ListOfFriends = FriendsService.getInstance().retriveNamesOffriends(username);
	for (String friendName : ListOfFriends) {
		String usernameOffriend = friendName;
		postingService.printPosts(usernameOffriend);

	}
}
}
