package org.project.bagusna.letsponder.views.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.project.bagusna.letsponder.models.Question;

import java.util.ArrayList;

public class QuestionListBlock extends VBox {
    private final String title;
    private final ArrayList<Question> questions;

    public QuestionListBlock(String title, ArrayList<Question> questions) {
        this.title = title;
        this.questions = questions;

        this.setSpacing(8);

        this.getChildren().addAll(
                this.getTitleLabel(),
                this.getQuestionListComponent()
        );
    }

    public Label getTitleLabel() {
        Label title = new Label(this.title);
        title.setFont(Font.font(20));

        return title;
    }

    public VBox getQuestionListComponent() {
        VBox container = new VBox();

        if (this.questions == null) {
            return container;
        }

        for (Question question: this.questions) {
            QuestionItem item = new QuestionItem(question);
            container.getChildren().add(item);
            System.out.println(item.getWidth());
        }

        System.out.println(container.getChildren());

        return container;
    }
}
