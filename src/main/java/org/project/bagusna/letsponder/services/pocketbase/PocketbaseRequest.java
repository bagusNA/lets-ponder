package org.project.bagusna.letsponder.services.pocketbase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class PocketbaseRequest {
    private final String baseUrl;
    private final String collectionName;
    private String id;

    private Integer page;
    private Integer perPage;
    private String[] sort;
    private String filter;
    private String[] expand;
    private String[] fields;
    private boolean skipTotal;

    public PocketbaseRequest(String baseUrl, String collectionName) {
        this.baseUrl = baseUrl;
        this.collectionName = collectionName;
    }

    public PocketbaseRequest(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.collectionName = builder.collectionName;
        this.id = builder.id;
        this.page = builder.page;
        this.perPage = builder.perPage;
        this.filter = builder.filter;
        this.skipTotal = builder.skipTotal;

        if (builder.sort != null) {
            this.sort = builder.sort.toArray(new String[0]);
        }

        if (builder.expand != null) {
            this.expand = builder.expand.toArray(new String[0]);
        }

        if (builder.fields != null) {
            this.fields = builder.fields.toArray(new String[0]);
        }
    }

    public HttpResponse<String> send() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(this.getUrl());

        System.out.println(uri.getRawPath());

        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public String getUrl() {
        String url = this.baseUrl + "/api/collections/" + this.collectionName + "/records";

        if (this.id != null) {
            url += "/" + this.id;
        }

        return url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String[] getExpand() {
        return expand;
    }

    public void setExpand(String[] expand) {
        this.expand = expand;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public boolean isSkipTotal() {
        return skipTotal;
    }

    public void setSkipTotal(Boolean skipTotal) {
        if (skipTotal == null) {
            this.skipTotal = false;
            return;
        }

        this.skipTotal = skipTotal;
    }

    public static class Builder {
        private String baseUrl;
        private String collectionName;
        private String id;

        private Integer page;
        private Integer perPage;
        private ArrayList<String> sort;
        private String filter;
        private ArrayList<String> expand;
        private ArrayList<String> fields;
        private boolean skipTotal;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder collection(String collectionName) {
            this.collectionName = collectionName;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder perPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public Builder sortBy(String field) {
            this.sort.add(field);
            return this;
        }

        public Builder sortBy(String[] fields) {
            this.sort.addAll(Arrays.asList(fields));
            return this;
        }

        public Builder filter(String filter) {
            this.filter = filter;
            return this;
        }

        public Builder relations(String relation) {
            this.expand.add(relation);
            return this;
        }

        public Builder relations(String[] relations) {
            this.expand.addAll(Arrays.asList(relations));
            return this;
        }

        public Builder select(String field) {
            this.fields.add(field);
            return this;
        }

        public Builder select(String[] fields) {
            this.fields.addAll(Arrays.asList(fields));
            return this;
        }

        public Builder withCount(boolean withCount) {
            this.skipTotal = !withCount;
            return this;
        }

        public PocketbaseRequest build() {
            return new PocketbaseRequest(this);
        }
    }
}
