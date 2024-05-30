package org.project.bagusna.letsponder.stores;

import javafx.beans.property.SimpleObjectProperty;
import org.project.bagusna.letsponder.models.Question;

import java.util.function.Consumer;

public class QuestionStore implements Store<Question> {
    private static QuestionStore INSTANCE;

    private final SimpleObjectProperty<Question> question;

    public QuestionStore() {
        this.question = new SimpleObjectProperty<>();
    }

    public static QuestionStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuestionStore();
        }

        return INSTANCE;
    }

    public void set(Question question) {
        this.question.set(question);
    }

    public SimpleObjectProperty<Question> get() {
        return this.question;
    }

    public void clear() {
        this.question.set(null);
    }

    public void subscribe(Consumer<Question> consumer) {
        this.question.subscribe(consumer);
    }
}
