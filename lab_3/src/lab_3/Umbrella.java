package lab_3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Umbrella extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600);

        Ellipse el1 = new Ellipse(228, 167, 80.5, 80.5);
        el1.setFill(Color.MAGENTA);
        root.getChildren().add(el1);

        Ellipse el2 = new Ellipse(177, 185, 39, 39);
        el2.setFill(Color.WHITE);
        root.getChildren().add(el2);

        Ellipse el3 = new Ellipse(231, 178, 40, 40);
        el3.setFill(Color.WHITE);
        root.getChildren().add(el3);

        Ellipse el4 = new Ellipse(277, 172, 45, 45);
        el4.setFill(Color.WHITE);
        root.getChildren().add(el4);

        Ellipse el5 = new Ellipse(231, 218, 64, 32);
        el5.setFill(Color.WHITE);
        root.getChildren().add(el5);

        MoveTo mt3 = new MoveTo(218, 118);
        LineTo lt2 = new LineTo(230, 220);
        QuadCurveTo qt8 = new QuadCurveTo(238, 228, 245, 220);
        LineTo lt3 = new LineTo(245, 212);
        Path handle = new Path();
        handle.setStrokeWidth(2);
        handle.setStroke(Color.GRAY);
        handle.getElements().addAll(mt3, lt2, qt8, lt3);
        root.getChildren().add(handle);

        MoveTo mt1 = new MoveTo(146, 161.5);
        QuadCurveTo qt1 = new QuadCurveTo(160, 100, 211, 86);
        QuadCurveTo qt2 = new QuadCurveTo(277, 119, 250, 126);
        QuadCurveTo qt3 = new QuadCurveTo(217, 117, 188, 134);
        QuadCurveTo qt4 = new QuadCurveTo(164, 140, 146, 161.5);
        QuadCurveTo qt5 = new QuadCurveTo(275, 76, 301, 132);
        Path top = new Path();
        top.setStrokeWidth(2);
        top.setStroke(Color.WHITE);
        top.setFill(Color.DARKMAGENTA);
        top.getElements().addAll(mt1, qt1, qt5, qt2, qt3, qt4);
        root.getChildren().add(top);

        MoveTo mt2 = new MoveTo(211, 86);
        LineTo lt1 = new LineTo(211, 76);
        Path pin = new Path();
        pin.setStrokeWidth(2);
        pin.setStroke(Color.BLACK);
        pin.getElements().addAll(mt2, lt1);
        root.getChildren().add(pin);

        QuadCurveTo qt6 = new QuadCurveTo(191, 110, 190, 132);
        QuadCurveTo qt7 = new QuadCurveTo(240, 100, 250, 126);
        Path lines = new Path();
        lines.setStrokeWidth(2);
        lines.setStroke(Color.WHITE);
        lines.getElements().addAll(mt2, qt6, mt2, qt7);
        root.getChildren().add(lines);


        // Animation
        int cycleCount = 2;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(150);
        translateTransition.setToX(500);
        translateTransition.setCycleCount(cycleCount + 1);
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time), root);
        translateTransition2.setFromX(50);
        translateTransition2.setToX(500);
        translateTransition2.setCycleCount(cycleCount + 1);
        translateTransition2.setAutoReverse(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), root);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                fadeTransition,
                scaleTransition,
                translateTransition
        );

        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
        // End of animation

        //End
        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
