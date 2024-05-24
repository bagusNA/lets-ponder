package org.project.bagusna.letsponder.services.pocketbase;

import org.project.bagusna.letsponder.models.User;

import java.io.IOException;

public class PocketbaseService {
    private final String baseUrl;

    public PocketbaseService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public PocketbaseRequest.Builder getBuilder() {
        return new PocketbaseRequest.Builder()
                .baseUrl(this.baseUrl);
    }
    
    public void saveUser(User user) throws IOException {
        System.out.println("Saving user to Pocketbase: " + user.getUsername());
    }
}
