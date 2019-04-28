package com.imaginea.socialnetwork.service;

public class LogoutService {
   AuthenticationService authenticationService=new AuthenticationService();
	public void logout(String username)
	{   if(authenticationService.isUserNameAlreadyExists(username))
		System.out.println("Logout is successful!");
	else
		System.out.println("Logout is failed!"); 
	}
	 
}
