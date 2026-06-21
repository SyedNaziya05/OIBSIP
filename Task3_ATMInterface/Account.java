 package Task3_ATMInterface;

// public class Account {
    
// }
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account opened with balance: " + balance);
    }

    public String getUserId() {
        return userId;
    }

    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount + " | New Balance: " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount + " | New Balance: " + balance);
        return true;
    }

    public void recordTransferOut(double amount, String toUserId) {
        transactionHistory.add("Transferred: " + amount + " to User " + toUserId + " | New Balance: " + balance);
    }

    public void recordTransferIn(double amount, String fromUserId) {
        transactionHistory.add("Received: " + amount + " from User " + fromUserId + " | New Balance: " + balance);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}