package com.imaginea.socialnetwork.application;

import java.util.Scanner;

import com.imaginea.socialnetwork.service.EntryMessageService;
import com.imaginea.socialnetwork.service.LoginService;
import com.imaginea.socialnetwork.service.ProcessEntryInputService;
import com.imaginea.socialnetwork.service.RegistrationService;

public class SocialNetworkEndpoint {

	static Scanner scanner = new Scanner(System.in);

	private static EntryMessageService entryMessageService = new EntryMessageService();
	private static ProcessEntryInputService entryInputService = new ProcessEntryInputService();

	public static void main(String[] args) {

		while (true) {

			entryMessageService.entryMessage();
			entryInputService.processEntryInput();

		}

	}

}
