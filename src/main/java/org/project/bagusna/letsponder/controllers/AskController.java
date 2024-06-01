package org.project.bagusna.letsponder.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.project.bagusna.letsponder.dto.formrequests.CreateQuestionFormRequest;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.models.Topic;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.repositories.TopicRepository;
import org.project.bagusna.letsponder.services.question.QuestionService;
import org.project.bagusna.letsponder.stores.AuthStore;
import org.project.bagusna.letsponder.stores.QuestionStore;
import org.project.bagusna.letsponder.views.components.Toast;

import java.io.IOException;
import java.net.URISyntaxException;

public class AskController extends Controller {
    private final QuestionService questionService;
    private final TopicRepository topicRepository;
    private final AuthStore authStore;

    private final ObservableList<Topic> topics;

    @FXML
    private ChoiceBox<Topic> topicChoiceBox;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea detailField;
    @FXML
    private Button backBtn;
    @FXML
    private Button imageBtn;
    @FXML
    private Button submitBtn;

    public AskController(QuestionService questionService, TopicRepository topicRepository) {
        this.questionService = questionService;
        this.topicRepository = topicRepository;

        this.authStore = AuthStore.getInstance();
        this.topics = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        this.backBtn.setOnAction(e -> this.router.openView("search"));
        this.submitBtn.setOnAction(e -> this.onSubmit());

        this.loadTopics();
    }

    private void onSubmit() {
        String title = this.titleField.getText();
        String detail = this.detailField.getText();
        User user = this.authStore.getRecord();
        Topic topic = this.topicChoiceBox.getValue();

        CreateQuestionFormRequest form = new CreateQuestionFormRequest(title, detail, user, topic);
        Question question = this.questionService.submitQuestion(form);

        this.router.openView("search");

        String toastMsg = (question != null)
                ? "Successfully submitted your question!"
                : "Failed to submit question!";

        Platform.runLater(() -> Toast.makeText(toastMsg, 3500, 300, 300));
    }

    private void loadTopics() {
        this.thread.execute(() -> {
            try {
                this.topics.addAll(this.topicRepository.getAll().getItems());
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> this.topicChoiceBox.setItems(this.topics));
        });
    }
}