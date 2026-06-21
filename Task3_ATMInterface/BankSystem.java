 package Task3_ATMInterface;

// public class BankSystem {
    
// }
import java.util.HashMap;
import java.util.Map;

public class BankSystem {

    private Map<String, Account> accounts;

    public BankSystem() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    public Account authenticate(String userId, String pin) {
        Account account = accounts.get(userId);
        if (account != null && account.checkPin(pin)) {
            return account;
        }
        return null;
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }
}