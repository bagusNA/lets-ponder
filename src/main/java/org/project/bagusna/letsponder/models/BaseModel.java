package org.project.bagusna.letsponder.models;

public abstract class BaseModel {
    public static String collectionName;
    protected String id;
    protected String created;
    protected String updated;

    public BaseModel(String id, String created, String updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
