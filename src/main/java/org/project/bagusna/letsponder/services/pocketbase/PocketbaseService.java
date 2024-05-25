package org.project.bagusna.letsponder.services.pocketbase;

import io.github.cdimascio.dotenv.Dotenv;
import org.project.bagusna.letsponder.models.User;

import java.io.IOException;

public class PocketbaseService {
    private final String baseUrl;

    public PocketbaseService() {
        Dotenv env = Dotenv.load();
        this.baseUrl = env.get("API_ENDPOINT");
    }

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
