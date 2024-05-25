package org.project.bagusna.letsponder.services;

import javafx.fxml.FXMLLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Service {
    public static void inject(FXMLLoader loader) {
        loader.setControllerFactory((Class<?> type) -> {
            try {
                for (Constructor<?> constructor: type.getConstructors()) {
                    ArrayList<Object> parameterInstances = new ArrayList<>();

                    for (Class<?> parameter: constructor.getParameterTypes()) {
                        parameterInstances.add(parameter.getDeclaredConstructor().newInstance());
                    }

                    return constructor.newInstance(parameterInstances.toArray());
                }

                return type.getDeclaredConstructor().newInstance();
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                   NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
