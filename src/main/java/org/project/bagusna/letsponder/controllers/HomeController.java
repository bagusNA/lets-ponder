package org.project.bagusna.letsponder.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.project.bagusna.letsponder.Router;
import org.project.bagusna.letsponder.models.Topic;
import org.project.bagusna.letsponder.repositories.QuestionRepository;
import org.project.bagusna.letsponder.repositories.TopicRepository;
import org.project.bagusna.letsponder.views.components.QuestionListBlock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HomeController {
    private final Router router;
    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private ArrayList<Topic> topics;

    @FXML
    private VBox mainContentContainer;

    public HomeController(QuestionRepository questionRepository, TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.router = Router.getInstance();
    }

    @FXML
    private void initialize() {
        try {
            this.topics = topicRepository.getAll().getItems();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Topic topic: this.topics) {
            QuestionListBlock block = this.buildTopicQuestionListBlock(topic);
            this.mainContentContainer.getChildren().add(block);
        }
    }

    @FXML
    public void onSearchInputClicked(MouseEvent event) {
        router.openView("search");
    }

    public QuestionListBlock buildTopicQuestionListBlock(Topic topic) {
        try {
            QuestionListBlock block = new QuestionListBlock(topic.getTitle(), questionRepository.getByTopic(topic).getItems());
            return block;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}