package Task3_ATMInterface;

// public class ATMOperations {
    
// }
import java.util.List;
import java.util.Scanner;

public class ATMOperations {

    private Account account;
    private Scanner scanner;

    public ATMOperations(Account account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        List<String> history = account.getTransactionHistory();
        for (String entry : history) {
            System.out.println(entry);
        }
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw: ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Withdrawal successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit() {
        System.out.print("\nEnter amount to deposit: ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        account.deposit(amount);
        System.out.println("Deposit successful. New Balance: " + account.getBalance());
    }

    public void transfer(BankSystem bank) {
        System.out.print("\nEnter recipient User ID: ");
        String recipientId = scanner.next();

        Account recipient = bank.getAccount(recipientId);
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipientId.equals(account.getUserId())) {
            System.out.println("Cannot transfer to your own account.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        boolean success = account.withdraw(amount);
        if (success) {
            recipient.deposit(amount);
            account.recordTransferOut(amount, recipient.getUserId());
            recipient.recordTransferIn(amount, account.getUserId());
            System.out.println("Transfer successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private double readAmount() {
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else {
            scanner.next(); // discard invalid input
            return -1;
        }
    }
}