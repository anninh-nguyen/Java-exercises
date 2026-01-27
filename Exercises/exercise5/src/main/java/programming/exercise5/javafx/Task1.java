package programming.exercise5.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Task 1 (a) ---------------------------------------------------
        primaryStage.setWidth(350);
        primaryStage.setHeight(250);
        primaryStage.setX(200);

        Scene primaryScene = new Scene(new StackPane(new Button("Hello, JavaFX")), 300, 250);
        primaryScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(primaryScene);
        primaryStage.setX(50);
        primaryStage.setTitle("Task #1 (a)");
        primaryStage.show();


        // Task 1 (b) ---------------------------------------------------
        Button b1 = new Button("SomeButton");
        Button b2 = new Button("Another Button");
        TextField tf1 = new TextField("Hello, JavaFX");
        
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(b1,b2,tf1);

        Scene secondScene = new Scene(hBox, 400, 250);
        secondScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        var secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setX(400);
        primaryStage.setTitle("Task #1 (b)");
        secondStage.show();


        // Task 1 (c) ---------------------------------------------------
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30,30,30,30));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(new Text("Name"),0,0);
        gridPane.add(new TextField(""),1,0);
        gridPane.add(new Text("Email"),0,1);
        gridPane.add(new TextField(""),1,1);
        gridPane.add(new Text("Phone"),1,1);
        gridPane.add(new TextField(""),1,1);
        gridPane.add(new Text("City"), 0, 2);
        gridPane.add(new TextField(""), 1, 2);
        gridPane.add(new CheckBox("I Consent to process information"), 1, 3);
        gridPane.add(new Button("OK"),0,4);
        gridPane.add(new Button("Cancel"),1,4);

        Scene thirdScene = new Scene(gridPane, 500, 300);
        thirdScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage thirdStage = new Stage();
        thirdStage.setScene(thirdScene);
        thirdStage.setX(800);
        thirdStage.setTitle("Task #1 (c)");
        thirdStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
