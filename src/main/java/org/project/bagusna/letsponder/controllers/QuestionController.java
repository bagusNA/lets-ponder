package org.project.bagusna.letsponder.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.project.bagusna.letsponder.models.Answer;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.repositories.AnswerRepository;
import org.project.bagusna.letsponder.repositories.QuestionRepository;
import org.project.bagusna.letsponder.repositories.UserRepository;
import org.project.bagusna.letsponder.stores.QuestionStore;
import org.project.bagusna.letsponder.utils.DateUtil;
import org.project.bagusna.letsponder.utils.ImageUtil;
import org.project.bagusna.letsponder.views.components.AnswerBlock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionController extends Controller {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionStore questionStore;

    private String questionId;
    private ArrayList<Answer> answers;
    private HashMap<String, User> answerers;

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

    public QuestionController(QuestionRepository questionRepository, AnswerRepository answerRepository, UserRepository userRepository) {
        super();

        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionStore = QuestionStore.getInstance();
        this.answers = new ArrayList<>();
        this.answerers = new HashMap<>();
    }

    @FXML
    private void initialize() {
        this.loadUserInfo();
        this.loadQuestions();

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

    private void loadQuestions() {
        this.questionStore.subscribe(question -> {
            if (question == null || question.getId().equals(this.questionId)) {
                return;
            }
            else {
                this.questionId = question.getId();
                this.answers.clear();
                this.questionsContainer.getChildren().clear();
            }

            this.thread.execute(() -> {
                try {
                    this.answers.addAll(this.answerRepository.getByQuestionId(question.getId()).getItems());

                    String[] answererIds = this.answers.stream()
                            .map(Answer::getUser)
                            .toArray(String[]::new);

                    ArrayList<User> answerers = this.userRepository.getByIds(answererIds).getItems();

                    for (Answer answer: this.answers) {
                        for (User answerer: answerers) {
                            if (answer.getUser().equals(answerer.getId())) {
                                this.answerers.put(answer.getUser(), answerer);
                                break;
                            }
                        }
                    }

                    Platform.runLater(() -> {
                        int i = 0;
                        for (Answer answer: this.answers) {
                            User user = this.answerers.get(answer.getUser());

                            AnswerBlock block = new AnswerBlock(answer, user);
                            this.questionsContainer.getChildren().add(block);
                        }
                    });
                }
                catch (URISyntaxException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private void loadUserInfo() {
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
