package org.project.bagusna.letsponder.stores;

public interface Store<T> {
    void set(T object);

    T get();

    void clear();
}
