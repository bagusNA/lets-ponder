package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

public class UserRepository extends Repository {
    public UserRepository(PocketbaseService pbService) {
        super(pbService, "users");
    }
}
