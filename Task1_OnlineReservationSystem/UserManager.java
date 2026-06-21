package Task1_OnlineReservationSystem;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, String> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void addUser(String loginId, String password) {
        users.put(loginId, password);
    }

    public boolean authenticate(String loginId, String password) {
        return users.containsKey(loginId) && users.get(loginId).equals(password);
    }
}