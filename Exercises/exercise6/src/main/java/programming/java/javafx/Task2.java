package programming.java.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Task2 extends Application {

    public enum Operator {
        ADD, SUB, MUL, DIV, NULL
    }

    static int operator1 = 0;
    static int operator2 = 0;
    static  Operator operator = Operator.NULL;
    Text formular = new Text("0");
    
    @Override
    public void start(Stage stage) throws Exception {
        formular.setTextAlignment(TextAlignment.RIGHT);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Button bt1 = new Button(" 1 ");
        bt1.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(1);
            }
        });
        Button bt2 = new Button(" 2 ");
        bt2.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(2);
            }
        });
        Button bt3 = new Button(" 3 ");
        bt3.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(3);
            }
        });
        Button bt4 = new Button(" 4 ");
        bt4.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(4);
            }
        });
        Button bt5 = new Button(" 5 ");
        bt5.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(5);
            }
        });
        Button bt6 = new Button(" 6 ");
        bt6.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(6);
            }
        });
        Button bt7 = new Button(" 7 ");
        bt7.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(7);
            }
        });
        Button bt8 = new Button(" 8 ");
        bt8.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(8);
            }
        });
        Button bt9 = new Button(" 9 ");
        bt9.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(9);
            }
        });
        Button bt0 = new Button(" 0 ");
        bt0.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                cummulate(0);
            }
        });
        Button btAdd = new Button(" +");
        btAdd.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                operator = Operator.ADD;
            }
        });
        Button btSubtract = new Button(" - ");
        btAdd.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                operator = Operator.SUB;
            }
        });
        Button btMultply = new Button(" x ");
        btAdd.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                operator = Operator.MUL;
            }
        });
        Button btDevide = new Button(" / ");
        btAdd.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                operator = Operator.DIV;
            }
        });
        Button btEqual = new Button(" = ");
        btEqual.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                calculate();
            }
        });
        Button btClear = new Button("AC");
        btClear.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                clear();
            }
        });

        gridPane.add(bt7, 0, 0);
        gridPane.add(bt8, 1, 0);
        gridPane.add(bt9, 2, 0);
        gridPane.add(btAdd, 3, 0);
        gridPane.add(bt4, 0, 1);
        gridPane.add(bt5, 1, 1);
        gridPane.add(bt6, 2, 1);
        gridPane.add(btSubtract, 3, 1);
        gridPane.add(bt1, 0, 2);
        gridPane.add(bt2, 1, 2);
        gridPane.add(bt3, 2, 2);
        gridPane.add(btMultply, 3, 2);
        gridPane.add(btClear, 0, 3);
        gridPane.add(bt0, 1, 3);
        gridPane.add(btEqual, 2, 3);
        gridPane.add(btDevide, 3, 3);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(formular, gridPane);

        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DIGIT0: cummulate(0); break;
                    case DIGIT1: cummulate(1); break;
                    case DIGIT2: cummulate(2); break;
                    case DIGIT3: cummulate(3); break;
                    case DIGIT4: cummulate(4); break;
                    case DIGIT5: cummulate(5); break;
                    case DIGIT6: cummulate(6); break;
                    case DIGIT7: cummulate(7); break;
                    case DIGIT8: cummulate(8); break;
                    case DIGIT9: cummulate(9); break;
                    case PLUS: operator = Operator.ADD; break;
                    case MINUS: operator = Operator.SUB; break;
                    case ASTERISK: operator = Operator.MUL; break;
                    case SLASH: operator = Operator.DIV; break;
                    case ENTER: 
                    case EQUALS: calculate(); break;
                    case ESCAPE: clear(); break;

                    default: break;
                }
            }
        });

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void cummulate(int num) {
        if (operator == Operator.NULL) {// Operator 1
            operator1 = operator1 * 10 + num;
            formular.setText(Integer.toString(operator1));
        }
        else {
            operator2 = operator2 * 10 + num;
            formular.setText(Integer.toString(operator1) + " " + operator.toString() + " " + Integer.toString(operator2) );
        }
    }

    private void calculate(){
        int result = 0;
        switch (operator) {
            case ADD: result = operator1 + operator2; break;
            case SUB: result = operator1 - operator2; break;
            case MUL: result = operator1 * operator2; break;
            case DIV: if (operator2 != 0) {
                        result = operator1 / operator2;
                    } 
                    else {
                        formular.setText("Divide 0 Error!");
                        operator = Operator.NULL;
                        operator1 = 0;
                        operator2 = 0;
                        return;
                    } break;
            case NULL: break;
        }

        formular.setText(Integer.toString(result));

        operator = Operator.NULL;
        operator1 = result;
        operator2 = 0;
    }

    private void clear() {
        formular.setText("0");
        operator = Operator.NULL;
        operator1 = 0;
        operator2 = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
