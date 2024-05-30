package org.project.bagusna.letsponder.utils;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class ImageUtil {
    public static void circleImageView(ImageView view, double radius) {
        Circle circle = new Circle(radius / 2, radius / 2, radius / 2);
        view.setClip(circle);
    }
}
