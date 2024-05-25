package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

public class QuestionRepository extends BaseRepository<Question> {
    public QuestionRepository() {
        super(Question.collectionName, Question.class);
    }
}