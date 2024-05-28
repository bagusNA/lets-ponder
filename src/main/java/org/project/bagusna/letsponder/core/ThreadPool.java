package org.project.bagusna.letsponder.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static ExecutorService THREAD;

    public static ExecutorService getThread() {
        if (THREAD == null) {
            THREAD = Executors.newCachedThreadPool();
        }

        return THREAD;
    }
}
