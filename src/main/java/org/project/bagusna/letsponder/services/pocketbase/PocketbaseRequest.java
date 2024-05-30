package org.project.bagusna.letsponder.services.pocketbase;

import org.apache.http.client.utils.URIBuilder;
import org.project.bagusna.letsponder.dto.formrequests.FormRequest;

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
    private String slug;
    private boolean skipTotal;

    public PocketbaseRequest(String baseUrl, String collectionName) {
        this.baseUrl = baseUrl;
        this.collectionName = collectionName;
    }

    public PocketbaseRequest(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.collectionName = builder.collectionName;
        this.id = builder.id;
        this.slug = builder.slug;
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

    public HttpResponse<String> get() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(this.getUrl());

        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(FormRequest form) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(this.getUrl());

        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(form.toJson());
        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(body)
                .build();

        HttpClient client = HttpClient.newHttpClient();

        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public String getUrl() throws URISyntaxException {
        String formatString = "%s/api/collections/%s";

        if (this.slug != null) {
            formatString += "/" + this.slug;
        } else {
            formatString += "/records";
        }

        String url = String.format(formatString, this.baseUrl, this.collectionName);

        if (this.id != null) {
            url += "/" + this.id;
        }

        URIBuilder builder = new URIBuilder(url);

        if (this.page != null) {
            builder.addParameter("page", this.page.toString());
        }

        if (this.perPage != null) {
            builder.addParameter("perPage", this.perPage.toString());
        }

        if (this.filter != null) {
            builder.addParameter("filter", this.filter);
        }

        if (this.sort != null) {
            builder.addParameter("sort", String.join(",", this.sort));
        }

        if (this.expand != null) {
            builder.addParameter("expand", String.join(",", this.expand));
        }

        if (this.fields != null) {
            builder.addParameter("fields", String.join(",", this.fields));
        }

        if (this.skipTotal) {
            builder.addParameter("skipTotal", "true");
        }

        return builder.build().toString();
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
        private String slug;

        private Integer page;
        private Integer perPage;
        private ArrayList<String> sort = new ArrayList<>();
        private String filter;
        private ArrayList<String> expand = new ArrayList<>();
        private ArrayList<String> fields = new ArrayList<>();
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

        public Builder slug(String slug) {
            this.slug = slug;
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
