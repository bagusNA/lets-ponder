package org.project.bagusna.letsponder.models;

public class User extends BaseModel {
    public static String collectionName = "users";
    private String username;
    private String email;
    private String name;

    public User(String id, String username, String email, String name, String created, String updated) {
        super(id, created, updated);
        this.username = username;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
