package org.project.bagusna.letsponder.stores;

import org.project.bagusna.letsponder.models.User;

public class AuthStore implements Store<User> {
    private static AuthStore INSTANCE;

    private User user;

    public AuthStore() {}

    public static AuthStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthStore();
        }

        return INSTANCE;
    }

    @Override
    public void set(User user) {
        this.user = user;
    }

    @Override
    public User get() {
        return this.user;
    }

    @Override
    public void clear() {
        this.user = null;
    }
}
