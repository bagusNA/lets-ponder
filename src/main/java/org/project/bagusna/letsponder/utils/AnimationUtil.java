package org.project.bagusna.letsponder.utils;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationUtil {
    public static void fadeOnAndTranslate(Node node, int index, double delay, double duration, float fromX, float toX, float fromY, float toY) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        fadeTransition.pause();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);

        SequentialTransition sequentialTransition = new SequentialTransition(new PauseTransition(Duration.seconds(delay + ((float)index / 30))), parallelTransition);
        sequentialTransition.play();
    }
}
