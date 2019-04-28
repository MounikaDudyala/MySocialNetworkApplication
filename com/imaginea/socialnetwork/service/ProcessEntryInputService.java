package com.imaginea.socialnetwork.service;

import java.util.Scanner;

public class ProcessEntryInputService {
 Scanner scanner=new Scanner(System.in);
 private static RegistrationService registrationService=new RegistrationService();
	private static LoginService loginService=new LoginService();
	public void processEntryInput() {
	 String input= scanner.next();
	if(input==null)
		   throw new RuntimeException("please give your choice");
	 else if(input.equals("login"))
	      input="Login";
	 else if(input.equals("registration"))
		  input="Registration";
	 else
		 System.out.println("please give valid choice");
	
		switch (input) {
		case "Login":
			loginService.processLogin();
			break;
		case "Registration":
			registrationService.processRegistration();

		}
	}
}
