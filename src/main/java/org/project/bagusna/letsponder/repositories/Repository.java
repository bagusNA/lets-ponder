package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Repository {
    protected final PocketbaseService pbService;
    protected final String collectionName;

    public Repository(PocketbaseService pbService, String collectionName) {
        this.pbService = pbService;
        this.collectionName = collectionName;
    }

    public PocketbaseRequest.Builder getRequest() {
        return this.pbService.getBuilder()
                .collection(this.collectionName);
    }

    public void getAll() throws URISyntaxException, IOException, InterruptedException {
        PocketbaseRequest req = this.getRequest().build();
        req.send();
    }

    public void getDetail(String id) throws URISyntaxException, IOException, InterruptedException {
        PocketbaseRequest req = this.getRequest()
                .id(id)
                .build();

        req.send();
    }
}
