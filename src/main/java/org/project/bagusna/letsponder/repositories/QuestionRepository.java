package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Pagination;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class QuestionRepository extends BaseRepository<Question> {
    public QuestionRepository() {
        super(Question.collectionName, Question.class);
    }

    public Pagination<Question> getByTitle(String title) throws URISyntaxException, IOException, InterruptedException {
        String filter = String.format("title~'%s'", title);
        PocketbaseRequest req = this.getRequest().filter(filter).build();

        HttpResponse<String> res = req.get();
        return this.parsePagination(res.body());
    }
}