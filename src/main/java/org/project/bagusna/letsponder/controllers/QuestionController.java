package org.project.bagusna.letsponder.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.project.bagusna.letsponder.dto.formrequests.QuestionLikeFormRequest;
import org.project.bagusna.letsponder.models.*;
import org.project.bagusna.letsponder.repositories.AnswerRepository;
import org.project.bagusna.letsponder.repositories.QuestionLikeRepository;
import org.project.bagusna.letsponder.repositories.UserRepository;
import org.project.bagusna.letsponder.services.question.QuestionService;
import org.project.bagusna.letsponder.stores.AuthStore;
import org.project.bagusna.letsponder.stores.QuestionStore;
import org.project.bagusna.letsponder.utils.AnimationUtil;
import org.project.bagusna.letsponder.utils.DateUtil;
import org.project.bagusna.letsponder.utils.ImageUtil;
import org.project.bagusna.letsponder.views.components.AnswerBlock;
import org.project.bagusna.letsponder.views.components.Toast;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionController extends Controller {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionService questionService;
    private final QuestionLikeRepository questionLikeRepository;
    private final QuestionStore questionStore;
    private final AuthStore authStore;

    private ArrayList<Answer> answers;
    private HashMap<String, User> answerAuthors;

    @FXML
    private VBox answersContainer;
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
    @FXML
    private Button likeBtn;
    @FXML
    private Button answerBtn;

    public QuestionController(AnswerRepository answerRepository, UserRepository userRepository, QuestionService questionService, QuestionLikeRepository questionLikeRepository) {
        super();

        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionService = questionService;
        this.questionLikeRepository = questionLikeRepository;

        this.questionStore = QuestionStore.getInstance();
        this.authStore = AuthStore.getInstance();

        this.answers = new ArrayList<>();
        this.answerAuthors = new HashMap<>();
    }

    @FXML
    private void initialize() {
        this.loadQuestion();
        this.loadAnswers();

        this.questionStore.subscribe(this::loadLike);

        Platform.runLater(() -> {
            this.reportBtn.setOnAction(this::onReportAction);
            this.backBtn.setOnAction(this::onBackAction);
            this.answerBtn.setOnAction(this::onAnswerAction);
            this.likeBtn.setOnAction(this::onLikeAction);
        });
    }

    private void onReportAction(ActionEvent ev) {
        System.out.println(ev);
    }

    private void onBackAction(ActionEvent ev) {
        this.router.openView("search");
        this.questionStore.clear();
    }

    private void onAnswerAction(ActionEvent ev) {
        this.router.openView("answer");
        this.answers.clear();
    }

    private void onLikeAction(ActionEvent ev) {
        QuestionLikeFormRequest form = new QuestionLikeFormRequest(this.questionStore.get().getValue(), this.authStore.getRecord());

        this.thread.execute(() -> {
            QuestionLike like = this.questionService.like(form);
            this.loadLike(this.questionStore.get().get());

            String toastMsg = (like != null)
                    ? "Successfully added to liked questions!"
                    : "Failed to like this question!";

            Platform.runLater(() -> {
                Toast.makeText(toastMsg, 3500, 300, 300);

                if (like != null) {
                    FontIcon icon = (FontIcon) this.likeBtn.getGraphic();
                    icon.setIconLiteral("fas-heart");
                }
            });

        });
    }

    private void loadAnswers() {
        this.questionStore.subscribe(question -> {
            if (question == null) {
                return;
            }
            else {
                this.answers.clear();
                this.answersContainer.getChildren().clear();
            }

            this.thread.execute(() -> {
                try {
                    this.answers.addAll(this.answerRepository.getByQuestionId(question.getId()).getItems());

                    String[] answererIds = this.answers.stream()
                            .map(Answer::getUser)
                            .toArray(String[]::new);

                    ArrayList<User> allAuthors = this.userRepository.getByIds(answererIds).getItems();

                    for (Answer answer: this.answers) {
                        for (User answerer: allAuthors) {
                            if (answer.getUser().equals(answerer.getId())) {
                                this.answerAuthors.put(answer.getUser(), answerer);
                                break;
                            }
                        }
                    }

                    Platform.runLater(() -> {
                        int i = 0;
                        for (Answer answer: this.answers) {
                            User user = this.answerAuthors.get(answer.getUser());

                            AnswerBlock block = new AnswerBlock(answer, user);
                            this.answersContainer.getChildren().add(block);
                            AnimationUtil.fadeOnAndTranslate(block, i, 0.3, 0.5, -10, 0, 0, 0);

                            i++;
                        }
                    });
                }
                catch (URISyntaxException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
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

    private void loadLike(Question question) {
        this.thread.execute(() -> {
            try {
                Pagination<QuestionLike> likes = this.questionLikeRepository.getByQuestionId(question.getId());
                Integer likeCount = likes.getTotalItems();
                this.likeBtn.setText(likeCount.toString());

                Platform.runLater(() -> {
                    FontIcon icon = (FontIcon) this.likeBtn.getGraphic();
                    icon.setIconLiteral("far-heart");

                    for (QuestionLike like: likes.getItems()) {
                        if (like.getUser().equals(this.authStore.getRecord().getId())) {
                            icon.setIconLiteral("fas-heart");
                            break;
                        }
                    }
                });
            }
            catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void buildAvatarImage(String src) {
        Image avatar = new Image(src);
        ImageUtil.circleImageView(this.askerAvatar, this.askerAvatar.getFitWidth());
        this.askerAvatar.setImage(avatar);
    }
}
