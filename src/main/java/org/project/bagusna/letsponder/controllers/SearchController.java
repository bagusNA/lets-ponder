package org.project.bagusna.letsponder.controllers;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.repositories.QuestionRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SearchController extends Controller {
    private final QuestionRepository questionRepository;
    private ArrayList<Question> questions;

    @FXML
    private VBox listContainer;
    @FXML
    private TextField searchInput;
    @FXML
    private ProgressBar loadingBar;

    public SearchController(QuestionRepository questionRepository) {
        super();

        this.questionRepository = questionRepository;
    }

    @FXML
    public void initialize() {
        this.buildList();
    }

    @FXML
    private void onBackButtonAction() {
        this.router.openView("home");
    }

    @FXML
    private void searchAction() {
        String query = this.searchInput.getText();

        this.clearList();

        this.thread.execute(() -> {
            Platform.runLater(() -> {
                FadeTransition opacityTransition = new FadeTransition(Duration.millis(500), loadingBar);
                opacityTransition.setFromValue(0);
                opacityTransition.setToValue(1);
                opacityTransition.play();
            });

            try {
                this.questions = this.questionRepository.getByTitle(query).getItems();

                Platform.runLater(this::buildList);
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                FadeTransition opacityTransition = new FadeTransition(Duration.millis(500), loadingBar);
                opacityTransition.setFromValue(1);
                opacityTransition.setToValue(0);
                opacityTransition.play();
            });
        });
    }

    @FXML
    private void onListItemClicked(MouseEvent event, Question question) {
        System.out.println(question.getTitle());
    }

    private void buildList() {
        if (this.questions == null) {
            return;
        }

        for (Question question: this.questions) {
            this.buildListItem(question);
        }
    }

    private void buildListItem(Question question) {
        HBox rootContainer = new HBox();
        VBox statsContainer = new VBox();

        Label title = new Label(question.getTitle());
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

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

        statsContainer.setSpacing(8);
        statsContainer.getChildren().add(likeContainer);
        statsContainer.getChildren().add(discussionContainer);

        rootContainer.setSpacing(20);
        rootContainer.getChildren().add(title);
        rootContainer.getChildren().add(statsContainer);

        rootContainer.setOnMouseClicked(e -> this.onListItemClicked(e, question));

        this.listContainer.getChildren().add(rootContainer);
    }

    private void clearList() {
        this.listContainer.getChildren().clear();
    }
}
