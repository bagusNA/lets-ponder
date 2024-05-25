package org.project.bagusna.letsponder.dto.formrequests;

public class AuthLoginFormRequest extends FormRequest {
    public String identity;
    public String password;

    public AuthLoginFormRequest(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
