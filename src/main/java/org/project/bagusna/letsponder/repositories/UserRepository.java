package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public User findByUsername(String username) {
        return users.get(username);
    }

    public boolean usernameExists(String username) {
        return users.containsKey(username);
    }
}
