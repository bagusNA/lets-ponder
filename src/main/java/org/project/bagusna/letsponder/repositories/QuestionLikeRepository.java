package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Pagination;
import org.project.bagusna.letsponder.models.QuestionLike;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class QuestionLikeRepository extends BaseRepository<QuestionLike> {
    public QuestionLikeRepository() {
        super(QuestionLike.collectionName, QuestionLike.class);
    }

    public Pagination<QuestionLike> getByQuestionId(String questionId) throws URISyntaxException, IOException, InterruptedException {
        String filter = String.format("question='%s'", questionId);
        PocketbaseRequest req = this.getRequest().filter(filter).build();

        HttpResponse<String> res = req.get();
        return this.parsePagination(res.body());
    }
}
