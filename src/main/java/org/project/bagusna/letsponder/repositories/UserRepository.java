package org.project.bagusna.letsponder.repositories;

import org.project.bagusna.letsponder.models.Pagination;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class UserRepository extends BaseRepository<User> {
    public UserRepository() {
        super(User.collectionName, User.class);
    }

    public Pagination<User> getByIds(String[] idList) throws URISyntaxException, IOException, InterruptedException {
        String[] idFilters = Arrays.stream(idList)
                .map(id -> String.format("id='%s'", id))
                .toArray(String[]::new);

        String filter = String.join(" || ", idFilters);

        PocketbaseRequest req = this.getRequest().filter(filter).build();

        HttpResponse<String> res = req.get();

        return this.parsePagination(res.body());
    }
}
