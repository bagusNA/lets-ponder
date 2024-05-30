package org.project.bagusna.letsponder.dto.formrequests;

public class AuthRegisterFormRequest extends FormRequest {
    public String username;
    public String email;
    public String passwordConfirm;
    public String password;
    public boolean emailVisibility;
    public String role;
    public String about;

    public AuthRegisterFormRequest(String username, String email, String password, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordConfirm = passwordConfirm;

        this.emailVisibility = true;
        this.role = "LEARNER";
        this.about = "";
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
