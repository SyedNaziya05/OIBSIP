package Task1_OnlineReservationSystem;

import java.util.Scanner;

public class NaziyaSyed_Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        ReservationManager reservationManager = new ReservationManager();

        System.out.println("===================================");
        System.out.println("   ONLINE RESERVATION SYSTEM");
        System.out.println("===================================");

        // Seed a demo user
        userManager.addUser("naziya", "pass123");

        boolean loggedIn = false;
        int loginAttempts = 0;

        while (!loggedIn && loginAttempts < 3) {
            System.out.print("\nEnter Login ID: ");
            String loginId = scanner.next();
            System.out.print("Enter Password: ");
            String password = scanner.next();

            loggedIn = userManager.authenticate(loginId, password);

            if (!loggedIn) {
                loginAttempts++;
                System.out.println("Invalid credentials. Attempts left: " + (3 - loginAttempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts. Exiting...");
            scanner.close();
            return;
        }

        System.out.println("\nLogin successful! Welcome.");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readChoice(scanner);

            switch (choice) {
                case 1:
                    reservationManager.makeReservation(scanner);
                    break;
                case 2:
                    reservationManager.cancelReservation(scanner);
                    break;
                case 3:
                    reservationManager.viewAllReservations();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Online Reservation System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. Make a Reservation");
        System.out.println("2. Cancel a Reservation");
        System.out.println("3. View All Reservations");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static int readChoice(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next();
            return -1;
        }
    }
}