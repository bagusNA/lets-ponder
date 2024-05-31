package org.project.bagusna.letsponder.dto.formrequests;

import org.project.bagusna.letsponder.models.Topic;
import org.project.bagusna.letsponder.models.User;

public class CreateQuestionFormRequest extends FormRequest {
    public String title;
    public String detail;
    public String user;
    public String topics;
    public boolean isSolved;
    public boolean isArchived;

    public CreateQuestionFormRequest(String title, String detail, User user, Topic topics) {
        this.title = title;
        this.detail = detail;
        this.user = user.getId();
        this.topics = topics.getId();

        this.isSolved = false;
        this.isArchived = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
