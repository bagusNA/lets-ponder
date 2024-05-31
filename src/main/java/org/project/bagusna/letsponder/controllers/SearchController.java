package org.project.bagusna.letsponder.controllers;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.repositories.QuestionRepository;
import org.project.bagusna.letsponder.stores.QuestionStore;
import org.project.bagusna.letsponder.utils.AnimationUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SearchController extends Controller {
    private final QuestionRepository questionRepository;
    private final QuestionStore questionStore;
    private ArrayList<Question> questions;

    @FXML
    private VBox listContainer;
    @FXML
    private TextField searchInput;
    @FXML
    private ProgressBar loadingBar;
    @FXML
    private Button askBtn;

    public SearchController(QuestionRepository questionRepository) {
        super();

        this.questionRepository = questionRepository;
        this.questionStore = QuestionStore.getInstance();
    }

    @FXML
    public void initialize() {
        this.askBtn.setOnAction((ev) -> this.router.openView("ask"));

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
        this.questionStore.set(question);
        this.router.openView("question");
    }

    private void buildList() {
        if (this.questions == null) {
            return;
        }

        int i = 0;
        for (Question question: this.questions) {
            HBox item = this.buildListItem(question);
            AnimationUtil.fadeOnAndTranslate(item, i, 0.3, 0.5, -10, 0, 0, 0);

            i++;
        }
    }

    private HBox buildListItem(Question question) {
        HBox rootContainer = new HBox();
        VBox descriptionContainer = new VBox();
        VBox statsContainer = new VBox();

        Label title = new Label(question.getTitle());
        title.setFont(Font.font(20));
        title.setMaxWidth(Double.MAX_VALUE);

        Label description = new Label(question.getDetail());
        description.setFont(Font.font(13));
        description.setMaxWidth(Double.MAX_VALUE);
        description.setWrapText(true);

        HBox.setHgrow(descriptionContainer, Priority.ALWAYS);
        descriptionContainer.setSpacing(8);
        descriptionContainer.getChildren().addAll(title, description);

        HBox likeContainer = new HBox();
        FontIcon likeIcon = new FontIcon("fas-heart");
        Label likeCount = new Label("20");
        likeCount.setMinWidth(Label.USE_PREF_SIZE);
        likeContainer.setSpacing(12);
        likeContainer.setAlignment(Pos.CENTER_RIGHT);
        likeContainer.getChildren().add(likeIcon);
        likeContainer.getChildren().add(likeCount);

        HBox discussionContainer = new HBox();
        FontIcon discussionIcon = new FontIcon("far-comments");
        Label discussionCount = new Label("20");
        discussionCount.setMinWidth(Label.USE_PREF_SIZE);
        discussionContainer.setSpacing(12);
        discussionContainer.setAlignment(Pos.CENTER_RIGHT);
        discussionContainer.getChildren().addAll(discussionIcon, discussionCount);

        statsContainer.minWidth(200);
        statsContainer.setSpacing(8);
        statsContainer.getChildren().addAll(likeContainer, discussionContainer);

        rootContainer.setSpacing(20);
        rootContainer.getChildren().addAll(descriptionContainer, statsContainer);

        rootContainer.setOnMouseClicked(e -> this.onListItemClicked(e, question));

        this.listContainer.getChildren().add(rootContainer);

        return rootContainer;
    }

    private void clearList() {
        this.listContainer.getChildren().clear();
    }
}
