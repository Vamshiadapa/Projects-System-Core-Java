package onlinevoting;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("*********************************");
        System.out.println("# COLLEGE ONLINE VOTING SYSTEM  #");
        System.out.println("*********************************");
        do {
            printMenu(system);
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        handleRegister(system, scanner);
                        break;
                    case 2:
                        handleLogin(system, scanner);
                        break;
                    case 3:
                        handleLogout(system);
                        break;
                    case 4:
                        system.showCandidates();
                        break;
                    case 5:
                        handleVote(system, scanner);
                        System.out.println("Thank you. Goodbye!");
                        break;
                    case 6:
                        system.showResults();
                        break;
                    case 7:
                        System.out.println("Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Enter 1 to 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (1-7).");
                choice = 0;
            }
        } while (choice != 7);
        scanner.close();
    }
    private static void printMenu(VotingSystem system) {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
        System.out.println("4. View Candidates");
        System.out.println("5. Cast Vote");
        System.out.println("6. View Results");
        System.out.println("7. Exit");
        if (system.isLoggedIn()) {
            System.out.println("Logged in as: " + system.getCurrentUsername());
        } else {
            System.out.println("Status: Not logged in");
        }
    }
    private static void handleRegister(VotingSystem system, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter useridno: ");
        String useridno = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        try {
            system.register(username,useridno, password);
            System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void handleLogin(VotingSystem system, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter useridno: ");
        String useridno = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        try {
            system.login(username, useridno, password);
            System.out.println("Login successful! Welcome, " + username);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void handleLogout(VotingSystem system) {
        if (system.isLoggedIn()) {
            system.logout();
            System.out.println("Logged out successfully.");
        } else {
            System.out.println("You are not logged in.");
        }
    }
    private static void handleVote(VotingSystem system, Scanner scanner) {
        system.showCandidates();
        System.out.print("Enter candidate number to vote: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            system.castVote(index);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}