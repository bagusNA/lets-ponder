package org.project.bagusna.letsponder.models.enums;

public enum UserRole {
    LEARNER ("LEARNER"),
    MODERATOR ("MODERATOR"),
    ADMIN ("ADMIN");

    private final String value;

    UserRole(String learner) {
        this.value = learner;
    }

    public String getValue() {
        return value;
    }
}
