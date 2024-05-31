package org.project.bagusna.letsponder.models;
import java.util.ArrayList;
import java.util.List;

public class Topic extends BaseModel {
    public static String collectionName = "Topics";
    private String title;
    private String description;
    private List<GradeCategory> category;
    private List<Question> questions;

    public Topic(String id, String created, String updated, String title, String description) {
        super(id, created, updated);
        this.title = title;
        this.description = description;
        this.category = new ArrayList<>();
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

    public List<GradeCategory> getCategory() {
        return category;
    }

    public List<Question> getQyQuestions() {
        return questions;
    }

    public String toString() {
        return this.title;
    }
}
