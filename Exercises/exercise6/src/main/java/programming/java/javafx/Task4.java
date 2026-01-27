package programming.java.javafx;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Task4  extends Application {
   Circle targetCircle;
   Random rand = new Random();
   int width = 1200;
   int height = 800;
   int normalSpeed = 10;

    public void start(Stage primaryStage) {
      Circle circle1 = new Circle();
      circle1.setCenterX(50); circle1.setCenterY(70); circle1.setRadius(30);
      circle1.setStroke(Color.BLUEVIOLET);
      circle1.setFill(Color.rgb(rand.nextInt(254)+1,rand.nextInt(254)+1,rand.nextInt(254)+1, 
                        rand.nextInt(255) / 255.0)); //opacity 0.0 -> 1.00

      Circle circle2 = new Circle();
      circle2.setCenterX(70); circle2.setCenterY(100); circle2.setRadius(50);
      circle2.setStroke(Color.ALICEBLUE);
      circle2.setFill(Color.rgb(rand.nextInt(254)+1,rand.nextInt(254)+1,rand.nextInt(254)+1, 
                        rand.nextInt(255) / 255.0)); 
      Pane p = new Pane();
      p.getChildren().addAll(circle1, circle2);

      targetCircle = circle1;
      targetCircle.setStroke(Color.RED);
      
      // Handle a key event
      Scene scene = new Scene(p, width, height);
      scene.setOnKeyPressed(keyEvent->{
         KeyCode code = keyEvent.getCode();
         switch (code){
            case DIGIT1: 
               if (targetCircle == circle1) break;
               targetCircle = circle1; 
               targetCircle.setStroke(Color.RED);
               circle2.setStroke(Color.rgb(rand.nextInt(254)+1,rand.nextInt(254)+1, rand.nextInt(254)+1));
               break;
            case DIGIT2: 
               if (targetCircle == circle2) break;
               targetCircle = circle2; 
               targetCircle.setStroke(Color.RED);
               circle1.setStroke(Color.rgb(rand.nextInt(254)+1,rand.nextInt(254)+1, rand.nextInt(254)+1));
               break;
            case TAB:
               targetCircle.setFill(Color.rgb(rand.nextInt(254)+1,rand.nextInt(254)+1,
                                     rand.nextInt(254)+1, rand.nextInt(255) / 255.0));
               break;
            case A: 
               if (targetCircle.getRadius() + getSpeed(keyEvent) > getSpeed(keyEvent) + getSpeed(keyEvent)) {
                  targetCircle.setRadius(targetCircle.getRadius() - getSpeed(keyEvent)); 
               }
               break;
            case B: 
               if (targetCircle.getRadius() - getSpeed(keyEvent) < height/2 - getSpeed(keyEvent)) {
                  targetCircle.setRadius(targetCircle.getRadius() + getSpeed(keyEvent)); 
               }
               break;
            default: moveCircle(keyEvent); break;
         }
      });
      primaryStage.setScene(scene);
      primaryStage.setTitle("1: Selct Circle 1; 2: Select Circle 2; Tab: Change color; Arrow: Moving 10px; A: Scale down 10px; B: Scale up 10px; Shift: Boost");
      primaryStage.show();
   }

   private void moveCircle(KeyEvent keyEvent) {
      // check if circle reach scene boundary
      KeyCode code = keyEvent.getCode();
      if ((targetCircle.getCenterX() - targetCircle.getRadius() < getSpeed(keyEvent) && code == KeyCode.LEFT) // touch left
         || (targetCircle.getCenterX() + targetCircle.getRadius() > width - getSpeed(keyEvent) && code == KeyCode.RIGHT) // touch right
         || (targetCircle.getCenterY() - targetCircle.getRadius() < getSpeed(keyEvent) && code == KeyCode.UP) // touch top
         || (targetCircle.getCenterY() + targetCircle.getRadius() > height - getSpeed(keyEvent) && code == KeyCode.DOWN)// touch left
      ) {
         targetCircle.getParent().setStyle("-fx-background-color: #ffaa00");
         return;
      }
      else {
         targetCircle.getParent().setStyle("-fx-background-color: #ffffff");
      }
      switch (code) {
         case UP: targetCircle.setCenterY(targetCircle.getCenterY() - getSpeed(keyEvent)); break;
         case DOWN: targetCircle.setCenterY(targetCircle.getCenterY() + getSpeed(keyEvent)); break;
         case LEFT: targetCircle.setCenterX(targetCircle.getCenterX() - getSpeed(keyEvent)); break;
         case RIGHT: targetCircle.setCenterX(targetCircle.getCenterX() + getSpeed(keyEvent)); break;
         default:
            break;
      }
   }

   private int getSpeed(KeyEvent keyEvent) {
      return keyEvent.isShiftDown() ? normalSpeed * 3 : normalSpeed;
   }

   public static void main(String[] args) {
      launch(args);
   }
}
