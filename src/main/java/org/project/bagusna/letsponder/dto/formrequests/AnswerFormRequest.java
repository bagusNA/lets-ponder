package org.project.bagusna.letsponder.dto.formrequests;

public class AnswerFormRequest extends FormRequest {
    public String summary;
    public String detail;
    public String question;
    public String user;

    public AnswerFormRequest(String summary, String detail, String question, String user) {
        this.summary = summary;
        this.detail = detail;
        this.question = question;
        this.user = user;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
