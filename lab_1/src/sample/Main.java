package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 530, 320);
        scene.setFill(Color.rgb(128,128,255));

        Rectangle r = new Rectangle(110, 110, 250, 60);
        r.setFill(Color.WHITE);
        root.getChildren().add(r);

        Polygon stabilizer_top = new Polygon();
        stabilizer_top.getPoints().addAll(new Double[]{
                110.0, 70.0,
                160.0, 70.0,
                180.0, 110.0,
                130.0, 110.0,
        });
        stabilizer_top.setFill(Color.rgb(255, 128, 64));
        root.getChildren().add(stabilizer_top);

        Line stabilizer_mid = new Line(110.0f, 140.0f, 180.0f, 140.0f);
        stabilizer_mid.setStroke(Color.rgb(255, 128, 64));
        root.getChildren().add(stabilizer_mid);

        Polygon stabilizer_bot = new Polygon();
        stabilizer_bot.getPoints().addAll(new Double[]{
                130.0, 170.0,
                180.0, 170.0,
                160.0, 210.0,
                110.0, 210.0,
        });
        stabilizer_bot.setFill(Color.rgb(255, 128, 64));
        root.getChildren().add(stabilizer_bot);

        Polygon rocket_nose = new Polygon();
        rocket_nose.getPoints().addAll(new Double[]{
                360.0, 110.0,
                410.0, 140.0,
                360.0, 170.0,
        });
        rocket_nose.setFill(Color.rgb(255, 128, 64));
        root.getChildren().add(rocket_nose);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
