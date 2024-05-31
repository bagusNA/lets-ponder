package org.project.bagusna.letsponder.services.question;

import com.google.gson.Gson;
import org.project.bagusna.letsponder.dto.formrequests.AnswerFormRequest;
import org.project.bagusna.letsponder.dto.formrequests.CreateQuestionFormRequest;
import org.project.bagusna.letsponder.dto.formrequests.QuestionLikeFormRequest;
import org.project.bagusna.letsponder.models.Answer;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.models.QuestionLike;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class QuestionService {
    private final PocketbaseService pocketbaseService;
    private final Gson gson;

    public QuestionService() {
        this.pocketbaseService = new PocketbaseService();
        this.gson = new Gson();
    }

    public Question submitQuestion(CreateQuestionFormRequest form) {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(Question.collectionName)
                .build();

        try {
            HttpResponse<String> res = req.post(form);

            if (res.statusCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            return this.gson.fromJson(res.body(), Question.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Answer submitAnswer(AnswerFormRequest form) throws RuntimeException {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(Answer.collectionName)
                .build();

        try {
            HttpResponse<String> res = req.post(form);

            if (res.statusCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            return this.gson.fromJson(res.body(), Answer.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public QuestionLike like(QuestionLikeFormRequest form) {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(QuestionLike.collectionName)
                .build();

        try {
            HttpResponse<String> res = req.post(form);

            if (res.statusCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            return this.gson.fromJson(res.body(), QuestionLike.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
