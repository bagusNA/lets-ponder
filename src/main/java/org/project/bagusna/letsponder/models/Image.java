package org.project.bagusna.letsponder.models;

public class Image extends BaseModel {
    public static String collectionName = "images";
    private String title;
    private String description;
    private String path;
    
    public Image(String id, String created, String updated, String title, String description, String path) {
        super(id, created, updated);
        this.title = title;
        this.description = description;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
