package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.GradeCategory;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

public class GradeCategoryRepository extends BaseRepository<GradeCategory> {
    public GradeCategoryRepository() {
        super(GradeCategory.collectionName, GradeCategory.class);
    }
    
}