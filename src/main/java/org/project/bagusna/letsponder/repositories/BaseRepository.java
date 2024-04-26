package org.project.bagusna.letsponder.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.project.bagusna.letsponder.models.BaseModel;
import org.project.bagusna.letsponder.models.Pagination;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseRequest;
import org.project.bagusna.letsponder.services.pocketbase.PocketbaseService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public abstract class BaseRepository<T extends BaseModel> {
    protected final PocketbaseService pbService;
    protected final String collectionName;
    protected final Class<T> type;
    protected final Gson parser;

    public BaseRepository(PocketbaseService pbService, String collectionName, Class<T> type) {
        this.pbService = pbService;
        this.collectionName = collectionName;
        this.type = type;

        this.parser = new Gson();
    }

    public PocketbaseRequest.Builder getRequest() {
        return this.pbService.getBuilder()
                .collection(this.collectionName);
    }

    public Class<T> getModelType() {
        return this.type;
    }

    public Pagination<T> parsePagination(String data) {
        Type collectionType = TypeToken.getParameterized(Pagination.class, this.getModelType()).getType();

        return this.parser.fromJson(data, collectionType);
    }

    public T parseDetail(String data) {
        return this.parser.fromJson(data, this.getModelType());
    }

    public Pagination<T> getAll() throws URISyntaxException, IOException, InterruptedException {
        PocketbaseRequest req = this.getRequest().build();
        HttpResponse<String> res = req.send();

        return this.parsePagination(res.body());
    }

    public T getDetail(String id) throws URISyntaxException, IOException, InterruptedException {
        PocketbaseRequest req = this.getRequest().id(id).build();
        HttpResponse<String> res = req.send();

        return this.parseDetail(res.body());
    }
}
