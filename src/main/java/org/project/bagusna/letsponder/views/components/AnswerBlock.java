package org.project.bagusna.letsponder.views.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.kordamp.ikonli.javafx.FontIcon;
import org.project.bagusna.letsponder.models.Answer;
import org.project.bagusna.letsponder.models.User;
import org.project.bagusna.letsponder.utils.DateUtil;
import org.project.bagusna.letsponder.utils.ImageUtil;

public class AnswerBlock extends VBox {
    private final Answer answer;
    private final User user;

    public AnswerBlock(Answer answer, User user) {
        this.answer = answer;
        this.user = user;

        HBox userInfo = this.userInfoContainer();
        Label title = this.titleLabel();
        Label description = this.descLabel();
        HBox actionContainer = this.actionContainer();

        this.setPadding(new Insets(12, 16, 12, 16));
        this.setStyle(
                "-fx-border-radius: 10;" +
                "-fx-border-color: #B5C0D0;"
        );

        this.getChildren().addAll(userInfo, title, description, actionContainer);
    }

    public HBox userInfoContainer() {
        HBox root = new HBox();

        Image image = new Image(user.getProfileImageUrl());
        ImageView imageView = new ImageView();
        ImageUtil.circleImageView(imageView, 40);
        imageView.setImage(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        Label userName = new Label(user.getName());
        userName.setFont(Font.font("System", FontWeight.BOLD, 12));

        Label date = new Label(DateUtil.toHumanDate(answer.getCreated()));
        date.setFont(Font.font(10));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getChildren().addAll(userName, date);

        root.setSpacing(20);
        VBox.setMargin(root, new Insets(0, 0, 12, 0));
        root.getChildren().addAll(imageView, vbox);

        return root;
    }

    public Label titleLabel() {
        Label title = new Label(answer.getSummary());
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        title.setWrapText(true);
        title.setMinHeight(Region.USE_PREF_SIZE);
        VBox.setMargin(title, new Insets(0, 0, 8, 0));

        return title;
    }

    public Label descLabel() {
        Label desc = new Label(answer.getDetail());
        desc.setWrapText(true);
        desc.setMinHeight(Region.USE_PREF_SIZE);

        return desc;
    }

    public HBox actionContainer() {
        HBox root = new HBox();
        HBox statsContainer = this.statsContainer();
        HBox.setHgrow(statsContainer, Priority.ALWAYS);

        Button reportBtn = new Button();
        FontIcon icon = new FontIcon("fas-flag");
        reportBtn.setStyle("-fx-background-color: transparent");
        reportBtn.setGraphic(icon);

        HBox.setMargin(root, new Insets(8, 0, 0, 0));
        root.getChildren().addAll(statsContainer, reportBtn);

        return root;
    }

    public HBox statsContainer() {
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

        HBox statsContainer = new HBox();
        statsContainer.setSpacing(8);
        statsContainer.getChildren().addAll(likeContainer, discussionContainer);

        return statsContainer;
    }
}
