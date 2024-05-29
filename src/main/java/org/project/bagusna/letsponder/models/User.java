package org.project.bagusna.letsponder.models;

import io.github.cdimascio.dotenv.Dotenv;

public class User extends BaseModel {
    public static String collectionName = "users";
    private String username;
    private String email;
    private String name;
    private String role;
    private String about;
    private String profileImage;

    public User(String id, String username, String email, String name, String role, String about, String profileImage, String created, String updated) {
        super(id, created, updated);
        this.username = username;
        this.email = email;
        this.name = name;
        this.role = role;
        this.about = about;
        this.profileImage = profileImage;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileImageUrl() {
        String baseUrl = Dotenv.load().get("API_ENDPOINT");

        return String.format(
                "%s/api/files/%s/%s/%s",
                baseUrl,
                collectionName,
                this.id,
                this.profileImage
        );
    }
}
