package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Topic;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

public class TopicRepository extends BaseRepository<Topic> {
    public TopicRepository() {
        super(Topic.collectionName, Topic.class);
    }
}