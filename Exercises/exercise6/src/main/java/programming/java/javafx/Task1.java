package programming.java.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task1 extends Application {

    static boolean isColor1 = true;
    @Override
    public void start(Stage primaryStage) {

        // Task #1 (a) ----------------------------------------------
        // Create a buttons and a textfield
        Button b1 = new Button("BT1");
        TextField tf1 = new TextField("");
        tf1.setPrefColumnCount(50);
        b1.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                tf1.setText(tf1.getText() + "BT1 PRESSED!");
            }
        });
        HBox hbox1 = new HBox();
        hbox1.setSpacing(20); 
        hbox1.getChildren().addAll(b1, tf1);

        Button b2 = new Button("BT2");
        TextField tf2 = new TextField("");
        tf2.setPrefColumnCount(50);
        b2.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                tf2.setText(tf1.getText());
            }
        });
        HBox hbox2 = new HBox();
        hbox2.setSpacing(20); 
        hbox2.getChildren().addAll(b2, tf2);
        
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(30, 30, 30, 30));
        vbox.getChildren().addAll(hbox1, hbox2);

        Scene scene = new Scene(vbox, 750, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene); 
        primaryStage.setTitle("Task #1");
        primaryStage.show(); 


        // Task #1 (b) ----------------------------------------------
        Button bt3 = new Button("BT3");
        bt3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (isColor1){
                    vbox.setStyle("-fx-background-color: blue");
                }
                else {
                    vbox.setStyle("-fx-background-color: green");
                }

                isColor1 = !isColor1;
            }
        });
        vbox.getChildren().add(bt3);


        // Task #1 (c) ----------------------------------------------
        Button bt4 =  new Button("RESET");
        bt4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent e) {
                tf1.setText("");
                tf2.setText("");
                vbox.setStyle("-fx-background-color: none");
            }
        });
        vbox.getChildren().add(bt4);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
