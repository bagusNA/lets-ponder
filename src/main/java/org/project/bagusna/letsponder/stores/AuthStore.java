package org.project.bagusna.letsponder.stores;

import javafx.beans.property.SimpleObjectProperty;
import org.project.bagusna.letsponder.dto.auth.AuthRecord;

import java.util.function.Consumer;

public class AuthStore implements Store<AuthRecord> {
    private static AuthStore INSTANCE;

    private final SimpleObjectProperty<AuthRecord> authRecord;

    public AuthStore() {
        this.authRecord = new SimpleObjectProperty<>();
    }

    public static AuthStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthStore();
        }

        return INSTANCE;
    }

    @Override
    public void set(AuthRecord authRecord) {
        this.authRecord.set(authRecord);
    }

    @Override
    public SimpleObjectProperty<AuthRecord> get() {
        return this.authRecord;
    }

    @Override
    public void clear() {
        this.authRecord.set(null);
    }

    @Override
    public void subscribe(Consumer<AuthRecord> consumer) {
        this.authRecord.subscribe(consumer);
    }

    public boolean isAuthenticated() {
        AuthRecord authRecord = this.authRecord.get();

        if (authRecord == null) {
            return false;
        }

        return authRecord.getToken() != null;
    }

    public String getToken() {
        return this.authRecord.get().getToken();
    }
}
