package org.project.bagusna.letsponder.models;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseModel {
    public static String collectionName = "questions";
    private String title;
    private String content;
    private String user;
    private String detail;
    private boolean isClosed;
    private String closedAt;
    private List<Answer> answers;
    private List<Answer> solutionAnswers;
    //private List<Image> attachments; 
    

    public Question(String id, String created, String updated, String title, String content,
    String user, boolean isClosed, String closedAt, String detail) {
        super(id, created, updated);
        this.title = title;
        this.content = content;
        this.user = user;
        this.detail = detail;
        this.isClosed = isClosed;
        this.closedAt = closedAt;
        this.answers = new ArrayList<>();
        this.solutionAnswers = new ArrayList<>();
        //this.attachments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Answer> getSolutionAnswers() {
        return solutionAnswers;
    }

    // public List<Image> getAttachments() {
    //     return attachments;
    // }
}