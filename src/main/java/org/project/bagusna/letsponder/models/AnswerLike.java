package org.project.bagusna.letsponder.models;

public class AnswerLike extends BaseModel {
    public static String collectionName = "answer_likes";
    public String answer;
    public String user;

    public AnswerLike(String id, String created, String updated, String answer, String user) {
        super(id, created, updated);
        this.answer = answer;
        this.user = user;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
