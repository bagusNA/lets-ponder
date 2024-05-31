package org.project.bagusna.letsponder.controllers;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.project.bagusna.letsponder.dto.formrequests.AnswerFormRequest;
import org.project.bagusna.letsponder.models.Question;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.repositories.UserRepository;
import org.project.bagusna.letsponder.services.question.QuestionService;
import org.project.bagusna.letsponder.stores.AuthStore;
import org.project.bagusna.letsponder.stores.QuestionStore;
import org.project.bagusna.letsponder.utils.DateUtil;
import org.project.bagusna.letsponder.utils.ImageUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class AnswerController extends Controller {
    private final UserRepository userRepository;
    private final QuestionService questionService;
    private final QuestionStore questionStore;
    private final AuthStore authStore;

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
    private TextField summaryField;
    @FXML
    private TextArea detailField;
    @FXML
    private Button backBtn;
    @FXML
    private Button imageBtn;
    @FXML
    private Button submitBtn;

    public AnswerController(UserRepository userRepository, QuestionService questionService) {
        this.userRepository = userRepository;
        this.questionService = questionService;
        this.authStore = AuthStore.getInstance();
        this.questionStore = QuestionStore.getInstance();
    }

    @FXML
    private void initialize() {
        this.backBtn.setOnAction(e -> this.router.openView("question"));
        this.submitBtn.setOnAction(e -> this.onSubmit());

        this.loadQuestion();
    }

    private void onSubmit() {
        String summary = this.summaryField.getText();
        String detail = this.detailField.getText();
        String userId = this.authStore.getRecord().getId();

        // workaround for triggering subscribe event, im too tired to use any other alternative.
        Question oldQuestion = this.questionStore.get().get();
        var gson = new Gson();
        var json = gson.toJson(oldQuestion);
        Question question = gson.fromJson(json, Question.class);

        AnswerFormRequest form = new AnswerFormRequest(summary, detail, question.getId(), userId);
        this.questionService.submitAnswer(form);
        this.questionStore.clear();
        this.questionStore.set(question);

        this.router.openView("question");
    }

    private void loadQuestion() {
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
    }

    private void buildAvatarImage(String src) {
        Image avatar = new Image(src);
        ImageUtil.circleImageView(this.askerAvatar, this.askerAvatar.getFitWidth());
        this.askerAvatar.setImage(avatar);
    }
}