package org.project.bagusna.letsponder.dto.formrequests;

import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.models.User;

public class QuestionLikeFormRequest extends LikeFormRequest {
    public String question;

    public QuestionLikeFormRequest(Question question, User user) {
        super(user);
        this.question = question.getId();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
