package Task3_ATMInterface;

// public class NaziyaSyed_Task3 {
    
// }
import java.util.Scanner;

public class NaziyaSyed_Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        // Seed a couple of demo accounts
        bank.addAccount(new Account("1001", "1234", 5000.0));
        bank.addAccount(new Account("1002", "5678", 10000.0));

        System.out.println("===================================");
        System.out.println("        WELCOME TO JAVA ATM");
        System.out.println("===================================");

        System.out.print("Enter User ID: ");
        String userId = scanner.next();

        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        Account account = bank.authenticate(userId, pin);

        if (account == null) {
            System.out.println("Invalid User ID or PIN. Exiting...");
            scanner.close();
            return;
        }

        System.out.println("\nLogin successful! Welcome, User " + account.getUserId() + ".");

        ATMOperations atm = new ATMOperations(account, scanner);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readChoice(scanner);

            switch (choice) {
                case 1:
                    atm.showTransactionHistory();
                    break;
                case 2:
                    atm.withdraw();
                    break;
                case 3:
                    atm.deposit();
                    break;
                case 4:
                    atm.transfer(bank);
                    break;
                case 5:
                    System.out.println("\nThank you for using Java ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------- ATM MENU -----------");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Choose an option: ");
    }

    private static int readChoice(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next(); // discard invalid input
            return -1;
        }
    }
}