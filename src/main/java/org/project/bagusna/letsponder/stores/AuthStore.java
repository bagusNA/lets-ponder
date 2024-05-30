package org.project.bagusna.letsponder.stores;

import javafx.beans.property.SimpleObjectProperty;
import org.project.bagusna.letsponder.models.User;

import java.util.function.Consumer;

public class AuthStore implements Store<User> {
    private static AuthStore INSTANCE;

    private final SimpleObjectProperty<User> user;

    public AuthStore() {
        this.user = new SimpleObjectProperty<>();
    }

    public static AuthStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthStore();
        }

        return INSTANCE;
    }

    @Override
    public void set(User user) {
        this.user.set(user);
    }

    @Override
    public SimpleObjectProperty<User> get() {
        return this.user;
    }

    @Override
    public void clear() {
        this.user.set(null);
    }

    @Override
    public void subscribe(Consumer<User> consumer) {
        this.user.subscribe(consumer);
    }
}
