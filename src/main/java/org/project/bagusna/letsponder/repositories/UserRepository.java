package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

public class UserRepository extends BaseRepository<User> {
    public UserRepository(PocketbaseService pbService) {
        super(pbService, User.collectionName, User.class);
    }
}
