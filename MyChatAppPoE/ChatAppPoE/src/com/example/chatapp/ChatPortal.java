package com.example.chatapp;

import java.util.Scanner;

public class ChatPortal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LoginManager authManager = new LoginManager();

        System.out.println("=================================");
        System.out.println("     CHAT APP LOGIN SYSTEM");
        System.out.println("=================================");

        // Registration section
        System.out.println("\nREGISTER NEW USER");

        System.out.print("Create username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Create password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Enter SA cellphone (+27...): ");
        String phoneNumber = scanner.nextLine();

        String registerFeedback = authManager.registerAccount(newUsername, newPassword, phoneNumber);
        System.out.println(registerFeedback);

        // Login section
        System.out.println("\nLOGIN TO ACCOUNT");

        System.out.print("Username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Password: ");
        String enteredPassword = scanner.nextLine();

        boolean isLoggedIn = authManager.verifyLogin(enteredUsername, enteredPassword);

        System.out.println(authManager.getLoginMessage(isLoggedIn, enteredUsername));

        scanner.close();
    }
}