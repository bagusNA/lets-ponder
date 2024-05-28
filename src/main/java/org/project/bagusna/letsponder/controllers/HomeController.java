package org.project.bagusna.letsponder.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.project.bagusna.letsponder.models.Topic;
import org.project.bagusna.letsponder.repositories.QuestionRepository;
import org.project.bagusna.letsponder.repositories.TopicRepository;
import org.project.bagusna.letsponder.views.components.QuestionListBlock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HomeController extends Controller {
    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private ArrayList<Topic> topics;

    @FXML
    private VBox mainContentContainer;

    public HomeController(QuestionRepository questionRepository, TopicRepository topicRepository) {
        super();

        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
    }

    @FXML
    private void initialize() {
        thread.execute(() -> {
            try {
                topics = topicRepository.getAll().getItems();
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                for (Topic topic: topics) {
                    QuestionListBlock block = buildTopicQuestionListBlock(topic);
                    mainContentContainer.getChildren().add(block);
                }
            });
        });
    }

    @FXML
    public void onSearchInputClicked(MouseEvent event) {
        router.openView("search");
    }

    public QuestionListBlock buildTopicQuestionListBlock(Topic topic) {
        try {
            return new QuestionListBlock(topic.getTitle(), questionRepository.getByTopic(topic).getItems());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}