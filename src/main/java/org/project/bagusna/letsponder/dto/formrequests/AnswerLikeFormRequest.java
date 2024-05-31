package org.project.bagusna.letsponder.dto.formrequests;

import org.project.bagusna.letsponder.models.Answer;
import org.project.bagusna.letsponder.models.User;

public class AnswerLikeFormRequest extends LikeFormRequest {
    public String answer;

    public AnswerLikeFormRequest(Answer answer, User user) {
        super(user);
        this.answer = answer.getId();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
