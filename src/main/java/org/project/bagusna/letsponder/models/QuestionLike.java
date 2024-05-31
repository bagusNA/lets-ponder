package org.project.bagusna.letsponder.models;

public class QuestionLike extends BaseModel {
    public static String collectionName = "question_likes";
    public String question;
    public String user;

    public QuestionLike(String id, String created, String updated, String question, String user) {
        super(id, created, updated);
        this.question = question;
        this.user = user;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
