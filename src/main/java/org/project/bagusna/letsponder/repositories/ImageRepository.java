package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Image;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

public class ImageRepository extends BaseRepository<Image> {
    public ImageRepository() {
        super(Image.collectionName, Image.class);
    }
}
