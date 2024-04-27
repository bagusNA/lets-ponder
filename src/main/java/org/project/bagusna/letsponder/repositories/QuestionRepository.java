package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.models.Pagination;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class QuestionRepository extends BaseRepository<Question> {


    public QuestionRepository(PocketbaseService pbService, AnswerRepository answerRepository) {
        super(pbService, Question.collectionName, Question.class);
    }

    public Pagination<Question> getByQuestionId(String questionId) throws URISyntaxException, IOException, InterruptedException {
        String filter = String.format("question='%s'", questionId);
        PocketbaseRequest req = this.getRequest().filter(filter).build();

        HttpResponse<String> res = req.send();
        return this.parsePagination(res.body());
    }
}