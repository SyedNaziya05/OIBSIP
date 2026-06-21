package Task4_OnlineExamination;

import java.util.HashMap;
import java.util.Map;

public class UserAccountManager {

    private Map<String, String> users;

    public UserAccountManager() {
        users = new HashMap<>();
    }

    public void addUser(String username, String password) {
        users.put(username, password);
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void updateProfile(String username) {
        System.out.println("\n--- Update Profile ---");
        System.out.print("Enter new password: ");
        String newPassword = InputReader.readLine().trim();

        users.put(username, newPassword);
        System.out.println("Password updated successfully.");
    }
}