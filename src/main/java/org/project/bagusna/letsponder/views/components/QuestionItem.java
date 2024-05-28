package org.project.bagusna.letsponder.views.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.javafx.FontIcon;
import org.project.bagusna.letsponder.models.Question;

public class QuestionItem extends HBox {
    public QuestionItem(Question question) {
        Label title = new Label(question.getTitle());
        HBox.setHgrow(title, Priority.ALWAYS);
        title.setMaxWidth(Double.MAX_VALUE);

        HBox likeContainer = new HBox();
        FontIcon likeIcon = new FontIcon("fas-heart");
        Label likeCount = new Label("20");
        likeContainer.setSpacing(12);
        likeContainer.setAlignment(Pos.CENTER_RIGHT);
        likeContainer.getChildren().add(likeIcon);
        likeContainer.getChildren().add(likeCount);

        HBox discussionContainer = new HBox();
        FontIcon discussionIcon = new FontIcon("far-comments");
        Label discussionCount = new Label("20");
        discussionContainer.setSpacing(12);
        discussionContainer.setAlignment(Pos.CENTER_RIGHT);
        discussionContainer.getChildren().add(discussionIcon);
        discussionContainer.getChildren().add(discussionCount);

        HBox statsContainer = new HBox();
        statsContainer.setSpacing(8);
        statsContainer.getChildren().addAll(likeContainer, discussionContainer);

        this.setSpacing(20);
        this.getChildren().addAll(title, statsContainer);
    }
}
