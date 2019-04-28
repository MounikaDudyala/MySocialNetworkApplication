package com.imaginea.socialnetwork.service;
import com.imaginea.socialnetwork.service.MenuService;
public class PostLogin {

	static MenuService menuService=new MenuService();
	 void postLogin(final String username) {
		if (username == null)
			throw new RuntimeException("Empty username - Can not find user.");
		System.out.println("Login is successfull");

		System.out.println("Browse through feed  OR chose one of the options from below.");
		menuService.printMenu();
		menuService.processMenu(username);
		
	}
}
