package programming.exercise5.javafx;

import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Task2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Task #2 (a)

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        

        gridPane.add(new Rectangle(50,40, Color.BLUEVIOLET), 0, 0);
        gridPane.add(new Circle(25, Color.ROYALBLUE), 0, 1);
        gridPane.add(new Ellipse(40, 20), 0, 2);

        gridPane.add(new Rectangle(50,40, Color.CADETBLUE), 1, 0);
        gridPane.add(new Ellipse(40, 20), 1, 1);
        gridPane.add(new Circle(25, Color.FUCHSIA), 1, 2);

        gridPane.add(new Ellipse(40, 20), 2, 0);
        gridPane.add(new Rectangle(50,40, Color.MINTCREAM), 2, 1);
        gridPane.add(new Circle(25, Color.TAN), 2, 2);;
        
        primaryStage.setX(50);
        primaryStage.setY(50);
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.setTitle("Task #2 (a)");
        primaryStage.show();


        // Task #2 (b)

        GridPane secondGridPane = new GridPane();
        secondGridPane.setHgap(10);
        secondGridPane.setVgap(10);
        secondGridPane.add(new ImageView(new Image("t21.jpg")), 0, 0);
        secondGridPane.add(new ImageView(new Image("t23.jpg")), 0, 1);
        secondGridPane.add(new ImageView(new Image("t22.jpg")), 1, 0);
        secondGridPane.add(new ImageView(new Image("t24.jpg")), 1, 1);

        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(secondGridPane));
        secondStage.setX(100);
        secondStage.setTitle("Task #2 (b)");
        secondStage.show();


        // Task #2 (c)
        
        GridPane thirdGridPane = new GridPane();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                thirdGridPane.add(new ImageView(new Image("t2" + i + ".jpg")), i, j);
            }
        }

        Stage thirdStage = new Stage();
        thirdStage.setScene(new Scene(thirdGridPane));
        thirdStage.setX(350);
        thirdStage.setTitle("Task #2 (c)");
        thirdStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
