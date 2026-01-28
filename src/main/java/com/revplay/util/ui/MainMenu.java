package com.revplay.util.ui;


import java.util.Scanner;

public class MainMenu {

    public static void showMainMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n WELCOME TO REVPLAY ");
            System.out.println("1. User");
            System.out.println("2. Artist");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    UserUI.userMenu();
                    break;
                case 2:
                    ArtistUI.artistMenu();
                    break;
                case 3:
                    System.out.println("Thank you for using RevPlay!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
