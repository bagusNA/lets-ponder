package org.project.bagusna.letsponder.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.models.Topic;
import org.project.bagusna.letsponder.repositories.QuestionRepository;
import org.project.bagusna.letsponder.repositories.TopicRepository;
import org.project.bagusna.letsponder.stores.AuthStore;
import org.project.bagusna.letsponder.utils.AnimationUtil;
import org.project.bagusna.letsponder.utils.ImageUtil;
import org.project.bagusna.letsponder.views.components.QuestionListBlock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeController extends Controller {
    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final AuthStore authStore;
    private ArrayList<Topic> topics;
    private HashMap<String, ArrayList<Question>> questions;

    @FXML
    private VBox mainContentContainer;
    @FXML
    private Button homeBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button discoverBtn;
    @FXML
    private Button userBtn;
    @FXML
    private ContextMenu userContextMenu;
    @FXML
    private MenuItem logoutContextItem;
    @FXML
    private ImageView userAvatarImageView;

    public HomeController(QuestionRepository questionRepository, TopicRepository topicRepository) {
        super();

        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.authStore = AuthStore.getInstance();

        this.questions = new HashMap<>();
    }

    @FXML
    private void initialize() {
        this.homeBtn.setOnAction((ActionEvent event) -> this.router.openView("home"));
        this.searchBtn.setOnAction((ActionEvent event) -> this.router.openView("search"));
        this.discoverBtn.setOnAction((ActionEvent event) -> this.router.openView("discover"));
        this.userBtn.setOnAction((ActionEvent event) -> userContextMenu.show(this.userBtn, Side.TOP, 0, 0));
        this.logoutContextItem.setOnAction((ActionEvent event) -> this.router.openView("login"));

        this.authStore.subscribe(authRecord -> {
            if (authRecord == null) {
                return;
            }

            this.userBtn.setText(authRecord.getRecord().getName());
            Platform.runLater(() -> this.buildAvatarImage(authRecord.getRecord().getProfileImageUrl()));
        });

        this.thread.execute(() -> {
            try {
                topics = topicRepository.getAll().getItems();

                for (Topic topic: topics) {
                    this.questions.put(topic.getId(), questionRepository.getByTopic(topic).getItems());
                }
            }
            catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                int i = 0;
                for (Topic topic: topics) {
                    QuestionListBlock block = new QuestionListBlock(topic.getTitle(), this.questions.get(topic.getId()));
                    mainContentContainer.getChildren().add(block);
                    AnimationUtil.fadeOnAndTranslate(block, i, 0.3, 0.5, -10, 0, 0, 0);

                    i++;
                }
            });
        });
    }

    @FXML
    public void onSearchInputClicked(MouseEvent event) {
        router.openView("search");
    }

    public void buildAvatarImage(String src) {
        Image avatar = new Image(src);
        ImageUtil.circleImageView(this.userAvatarImageView, this.userAvatarImageView.getFitWidth());
        this.userAvatarImageView.setImage(avatar);
    }
}