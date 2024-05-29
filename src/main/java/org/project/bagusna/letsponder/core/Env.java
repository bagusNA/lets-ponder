package org.project.bagusna.letsponder.core;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    public static Env INSTANCE;
    
    public Dotenv dotenv;
    
    public Env() {
        this.dotenv = Dotenv.load();
    }

    public static Env getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Env();
        }

        return INSTANCE;
    }

    public String get(String key) {
        return this.dotenv.get(key);
    }
}
