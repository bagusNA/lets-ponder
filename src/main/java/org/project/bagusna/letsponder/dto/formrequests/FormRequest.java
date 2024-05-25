package org.project.bagusna.letsponder.dto.formrequests;

import com.google.gson.Gson;

import java.io.Serializable;

public abstract class FormRequest implements Serializable {
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
