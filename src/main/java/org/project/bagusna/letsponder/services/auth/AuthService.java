package org.project.bagusna.letsponder.services.auth;

import com.google.gson.Gson;
import org.project.bagusna.letsponder.dto.auth.AuthRecord;
import org.project.bagusna.letsponder.dto.formrequests.AuthLoginFormRequest;
import org.project.bagusna.letsponder.dto.formrequests.AuthRegisterFormRequest;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class AuthService {
    private final PocketbaseService pocketbaseService;
    private final Gson gson;

    public AuthService() {
        this.pocketbaseService = new PocketbaseService();
        this.gson = new Gson();
    }

    public AuthRecord authenticate(String identity, String password) {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(User.collectionName)
                .slug("auth-with-password")
                .build();

        AuthLoginFormRequest body = new AuthLoginFormRequest(identity, password);

        try {
            HttpResponse<String> res = req.post(body);

            return this.gson.fromJson(res.body(), AuthRecord.class);
        }
        catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public User register(String name, String username, String email, String password, String confirmPassword) {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(User.collectionName)
                .build();

        AuthRegisterFormRequest body = new AuthRegisterFormRequest(name, username, email, password, confirmPassword);

        try {
            HttpResponse<String> res = req.post(body);

            if (res.statusCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            return this.gson.fromJson(res.body(), User.class);
        }
        catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
