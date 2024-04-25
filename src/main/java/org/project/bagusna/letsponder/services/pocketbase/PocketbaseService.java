package org.project.bagusna.letsponder.services.pocketbase;

public class PocketbaseService {
    private final String baseUrl;

    public PocketbaseService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public PocketbaseRequest.Builder getBuilder() {
        return new PocketbaseRequest.Builder()
                .baseUrl(this.baseUrl);
    }
}
