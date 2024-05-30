package org.project.bagusna.letsponder.stores;

import javafx.beans.property.SimpleObjectProperty;

import java.util.function.Consumer;

public interface Store<T> {
    void set(T object);

    SimpleObjectProperty<T> get();

    void clear();

    void subscribe(Consumer<T> consumer);
}
