package com.imaginea.socialnetwork.service;

import java.util.Scanner;

public class LoginService {
	public void processLogin() {
		 Scanner scanner=new Scanner(System.in);
	   AuthenticationService authenticationService = new AuthenticationService();
	   PostLogin postLogin=new PostLogin();
		System.out.println("Please enter your username and password");
		String username = scanner.next();
		String password = scanner.next();

		if (authenticationService.authenticate(username, password)) {
			if (username == null)
				throw new RuntimeException("Empty username - Can not find user.");
			postLogin.postLogin(username);

		} else
			System.out.println("Login is failed");
	}

}
