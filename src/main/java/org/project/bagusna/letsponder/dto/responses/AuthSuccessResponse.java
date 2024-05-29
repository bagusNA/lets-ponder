package org.project.bagusna.letsponder.dto.responses;

import org.project.bagusna.letsponder.models.User;

public class AuthSuccessResponse {
    public String token;
    public User record;

    public AuthSuccessResponse(String token, User record) {
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
