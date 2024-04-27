package org.project.bagusna.letsponder.models;
import java.util.ArrayList;
import java.util.List;

public class GradeCategory extends BaseModel {
    public static String collectionName = "gradecategory";
    private String title;
    private String description;
    private List<Topic> topics;
    private List<Question> questions;

    public GradeCategory(String id, String created, String updated, String title, String description) {
        super(id, created, updated);
        this.title = title;
        this.description = description;
        this.topics = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Question> getQyQuestions() {
        return questions;
    }

}
