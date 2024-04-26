package org.project.bagusna.letsponder.models;

public class Answer extends BaseModel {
    public static String collectionName = "answers";
    private String summary;
    private String detail;
    private boolean isSolution;
    private boolean isArchived;
    private String archivedAt;
    private String deletedAt;

    private String user;
    private String archivedBy;
    private String deletedBy;

    public Answer(String id, String created, String updated, String summary, String detail, boolean isSolution, boolean isArchived, String archivedAt, String deletedAt, String user, String archivedBy, String deletedBy) {
        super(id, created, updated);
        this.summary = summary;
        this.detail = detail;
        this.isSolution = isSolution;
        this.isArchived = isArchived;
        this.archivedAt = archivedAt;
        this.deletedAt = deletedAt;
        this.user = user;
        this.archivedBy = archivedBy;
        this.deletedBy = deletedBy;
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

    public boolean isSolution() {
        return isSolution;
    }

    public void setSolution(boolean solution) {
        isSolution = solution;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(String archivedAt) {
        this.archivedAt = archivedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getArchivedBy() {
        return archivedBy;
    }

    public void setArchivedBy(String archivedBy) {
        this.archivedBy = archivedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
