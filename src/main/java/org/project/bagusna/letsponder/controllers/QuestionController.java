package org.project.bagusna.letsponder.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.repositories.QuestionRepository;
import org.project.bagusna.letsponder.repositories.UserRepository;
import org.project.bagusna.letsponder.stores.QuestionStore;
import org.project.bagusna.letsponder.utils.DateUtil;
import org.project.bagusna.letsponder.utils.ImageUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class QuestionController extends Controller {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionStore questionStore;

    @FXML
    private VBox questionsContainer;
    @FXML
    private ImageView askerAvatar;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label questionCategoryLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button reportBtn;
    @FXML
    private Button backBtn;

    public QuestionController(QuestionRepository questionRepository, UserRepository userRepository) {
        super();

        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.questionStore = QuestionStore.getInstance();
    }

    @FXML
    private void initialize() {
        this.questionStore.subscribe(question -> {
            if (question == null) {
                return;
            }

            this.titleLabel.setText(question.getTitle());
            this.descriptionLabel.setText(question.getDetail());

            this.thread.execute(() -> {
                try {
                    User user = this.userRepository.getDetail(question.getUser());

                    Platform.runLater(() -> {
                        this.userNameLabel.setText(user.getName());
                        this.questionCategoryLabel.setText(DateUtil.toHumanDate(question.getCreated()));
                        buildAvatarImage(user.getProfileImageUrl());
                    });
                } catch (URISyntaxException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        Platform.runLater(() -> {
            this.reportBtn.setOnAction(this::onReportAction);
            this.backBtn.setOnAction(this::onBackAction);
        });
    }

    private void onReportAction(ActionEvent ev) {
        System.out.println(ev);
    }

    private void onBackAction(ActionEvent ev) {
        this.router.openView("search");
        this.questionStore.clear();
    }

    private void buildAvatarImage(String src) {
        Image avatar = new Image(src);
        ImageUtil.circleImageView(this.askerAvatar, this.askerAvatar.getFitWidth());
        this.askerAvatar.setImage(avatar);
    }
}
