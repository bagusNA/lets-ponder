package org.project.bagusna.letsponder.dto.auth;

import org.project.bagusna.letsponder.models.User;

public class AuthRecord {
    public String token;
    public User record;

    public AuthRecord(String token, User record) {
        this.token = token;
        this.record = record;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getRecord() {
        return record;
    }

    public void setRecord(User record) {
        this.record = record;
    }
}
