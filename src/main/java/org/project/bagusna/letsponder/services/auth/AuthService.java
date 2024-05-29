package org.project.bagusna.letsponder.services.auth;

import com.google.gson.Gson;
import org.project.bagusna.letsponder.dto.formrequests.AuthLoginFormRequest;
import org.project.bagusna.letsponder.dto.responses.AuthSuccessResponse;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class AuthService {
    private final PocketbaseService pocketbaseService;
    private final Gson gson;

    public AuthService() {
        this.pocketbaseService = new PocketbaseService();
        this.gson = new Gson();
    }

    public AuthSuccessResponse authenticate(String identity, String password) {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(User.collectionName)
                .slug("auth-with-password")
                .build();

        AuthLoginFormRequest body = new AuthLoginFormRequest(identity, password);

        try {
            HttpResponse<String> res = req.post(body);

            return this.gson.fromJson(res.body(), AuthSuccessResponse.class);
        }
        catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
