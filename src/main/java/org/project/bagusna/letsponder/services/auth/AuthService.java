package org.project.bagusna.letsponder.services.auth;

import org.project.bagusna.letsponder.dto.formrequests.AuthLoginFormRequest;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class AuthService {
    private final PocketbaseService pocketbaseService;

    public AuthService(PocketbaseService pocketbaseService) {
        this.pocketbaseService = pocketbaseService;
    }

    public boolean authenticate(String identity, String password) {
        PocketbaseRequest req = this.pocketbaseService.getBuilder()
                .collection(User.collectionName)
                .slug("auth-with-password")
                .build();

        AuthLoginFormRequest body = new AuthLoginFormRequest(identity, password);

        try {
            HttpResponse<String> res = req.post(body);
            return res.statusCode() == HttpURLConnection.HTTP_OK;
        }
        catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
