package org.project.bagusna.letsponder.controllers;

import org.project.bagusna.letsponder.core.Router;
import org.project.bagusna.letsponder.core.ThreadPool;

import java.util.concurrent.ExecutorService;

public class Controller {
    protected final Router router;
    protected final ExecutorService thread;

    public Controller() {
        this.router = Router.getInstance();
        this.thread = ThreadPool.getThread();
    }
}
