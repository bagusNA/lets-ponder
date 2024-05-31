package org.project.bagusna.letsponder.dto.formrequests;

import org.project.bagusna.letsponder.models.User;

public class LikeFormRequest extends FormRequest {
    public String user;

    public LikeFormRequest(User user) {
        this.user = user.getId();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
