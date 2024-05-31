package org.project.bagusna.letsponder.dto.formrequests;

import org.project.bagusna.letsponder.models.enums.UserRole;

public class AuthRegisterFormRequest extends FormRequest {
    public String name;
    public String username;
    public String email;
    public String passwordConfirm;
    public String password;
    public boolean emailVisibility;
    public String role;
    public String about;

    public AuthRegisterFormRequest(String name, String username, String email, String password, String passwordConfirm) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordConfirm = passwordConfirm;

        this.emailVisibility = true;
        this.role = UserRole.LEARNER.getValue();
        this.about = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
