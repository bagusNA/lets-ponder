package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Answer;
import org.project.bagusna.letsponder.models.Pagination;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class AnswerRepository extends BaseRepository<Answer> {
    public AnswerRepository(PocketbaseService pbService) {
        super(pbService, Answer.collectionName, Answer.class);
    }

    public Pagination<Answer> getByQuestionId(String questionId) throws URISyntaxException, IOException, InterruptedException {
        String filter = String.format("question='%s'", questionId);
        PocketbaseRequest req = this.getRequest().filter(filter).build();

        HttpResponse<String> res = req.send();
        return this.parsePagination(res.body());
    }
}
