package programming.exercise5.javafx;

import javafx.application.Application;
import javafx.css.FontFace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class App extends Application {

    public void start(Stage primaryStage) {
        
        var font = Font.font("Tahoma");

        primaryStage.setWidth(350);
        primaryStage.setHeight(250);
        primaryStage.setX(200);

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        l.setFont(font);
        Scene scene = new Scene(new StackPane(l), 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage secondaryStage = new Stage();
        secondaryStage.setWidth(200);
        secondaryStage.setHeight(250);
        secondaryStage.setX(550);
        Button btOK = new Button("OK");
        btOK.setFont(font);
        Scene scene2 = new Scene(new StackPane(btOK), 500, 400);
        secondaryStage.setScene(scene2);

        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
