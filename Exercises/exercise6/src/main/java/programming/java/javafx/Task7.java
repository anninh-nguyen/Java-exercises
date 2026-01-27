package programming.java.javafx;

import javafx.animation.Animation.Status;

import java.util.Random;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * Known issue:
 * - Cannot count missile fired 
 * - Missle hit did not remove missle
 * - Cannot pause weapon
 */

public class Task7 extends Application {

    final double battleWidth = 1200 ;
    final double battleHeight = 800 ;

    final double weaponLength = 50;
    final double weaponX = battleWidth - 100;
    final double weaponArea = 200;
    final double weaponStartY = battleHeight;
    final double weaponEndY = battleHeight - weaponLength;
    final double missileRadius = 10;
    final double bulletRadius = 5;
    int missileFiredCounter = 0;
    int missileHitCounter = 0;


    Ellipse ballistic1 = new Ellipse(); Circle missile1 = new Circle();
    Ellipse ballistic2 = new Ellipse(); Circle missile2 = new Circle();
    Ellipse ballistic3 = new Ellipse(); Circle missile3 = new Circle();

    @Override
    public void start(Stage stage) throws Exception {

        Text counter = new Text(10, battleHeight - 10, missileCount());

        // prepare weapon
        Line weapon = new Line(weaponX, weaponStartY, weaponX, weaponEndY);
        weapon.setStrokeWidth(5);
        Rotate weaponRotator = new Rotate(-45, weaponX, weaponStartY);
        weapon.getTransforms().add(weaponRotator);

        // prepare missile
        missile1.setRadius(missileRadius);
        // missile1.translateYProperty().addListener(e -> {
        //     System.out.println("Missile 1 (" + missile1.getTranslateX() + " : " + missile1.getTranslateY() + ") " + missile1.getTranslateY() + ", Balistic 1: " + ballistic1.getRadiusX() + ", " + ballistic1.getRadiusY());
        //     if (missile1.getTranslateY() == battleHeight - ballistic1.getRadiusY()) { // the missile is on top of the transition
        //         missileFiredCounter++;
        //         counter.setText(missileCount());
        //     }
        // });
        // missle1.translateYProperty().addListener(new ChangeListener<Number>() {
        //     @Override
        //     public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        //     double x = missle1.getTranslateX() - ballistic1.getTranslateX();
        //     double y = missle1.getTranslateY() - ballistic1.getTranslateY();
        //     double distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        //     System.out.println("Distance: "+ distance);
        //     if (distance < 50) {
        //         System.out.println("hit");
        //     }
        //     }
        // });
        missile2.setRadius(missileRadius);
        missile3.setRadius(missileRadius);
        
        // prepare battle
        ballistic1.setCenterX(0); ballistic1.setCenterY(battleHeight);  // at the lower left corner
        ballistic1.setRadiusX(battleWidth - weaponArea - 100); ballistic1.setRadiusY(battleHeight - 10);
        ballistic1.setFill(Color.rgb(0, 0, 0, 0)); ballistic1.setStroke(Color.WHITE);
        PathTransition missle1Path = new PathTransition(Duration.seconds(2.0),ballistic1, missile1);
        missle1Path.setCycleCount(1);
        missle1Path.cycleCountProperty().addListener(fireEvent -> {
            missileFiredCounter++;
            counter.setText(missileCount());
        });
        missle1Path.setOnFinished(e -> {
            missileFiredCounter++;
            counter.setText(missileCount());
        });
        missle1Path.play();

        ballistic2.setCenterX(0); ballistic2.setCenterY(battleHeight); 
        ballistic2.setRadiusX(battleWidth - weaponArea - 50); ballistic2.setRadiusY(battleHeight - 35);
        ballistic2.setFill(Color.rgb(0, 0, 0, 0)); ballistic2.setStroke(Color.WHITE);
        PathTransition missle2Path = new PathTransition(Duration.seconds(3.0),ballistic2, missile2);
        missle2Path.setCycleCount(Timeline.INDEFINITE);
        missle2Path.cycleCountProperty().addListener(fireEvent -> {
            missileFiredCounter++;
            counter.setText(missileCount());
        });
        missle2Path.play();

        ballistic3.setCenterX(0); ballistic3.setCenterY(battleHeight); 
        ballistic3.setRadiusX(battleWidth - weaponArea); ballistic3.setRadiusY(battleHeight - 60);
        ballistic3.setFill(Color.rgb(0, 0, 0, 0));  ballistic3.setStroke(Color.WHITE);
        PathTransition missle3Path = new PathTransition(Duration.seconds(4.0),ballistic3, missile3);
        missle3Path.setCycleCount(Timeline.INDEFINITE);
        missle3Path.cycleCountProperty().addListener(fireEvent -> {
            missileFiredCounter++;
            counter.setText(missileCount());
        });
        missle3Path.play();

        Pane battle = new Pane();
        battle.getChildren().addAll(ballistic1, ballistic2, ballistic3, missile1, missile2, missile3, weapon, counter);
        
        Scene scene = new Scene(battle, battleWidth, battleHeight);

        scene.setOnKeyPressed(keyEvent -> {
            var keyCode = keyEvent.getCode();
            switch (keyCode) {
                case UP:
                    weaponRotator.setAngle(Math.min(-30, weaponRotator.getAngle() + 2));
                    break;
                case DOWN:
                    weaponRotator.setAngle(Math.max(-80, weaponRotator.getAngle() - 2));
                    break;
                case SPACE:
                    Point2D weaponEnd = weapon.localToParent(weaponX, weaponEndY);
                    Circle bullet = new Circle(weaponX, battleHeight, bulletRadius);

                    TranslateTransition bulletFly = new TranslateTransition(Duration.seconds(1), bullet);
                    bulletFly.setByX(Math.tan(Math.toRadians(weaponRotator.getAngle())) * battleHeight);
                    bulletFly.setByY(-battleHeight);
                    bulletFly.setOnFinished(bulletEndEvent -> battle.getChildren().remove(bullet));

                    battle.getChildren().add(bullet); // Load bullet
                    bulletFly.play();

                    // Binding missle #1
                    BooleanBinding shotMissle1 = Bindings.createBooleanBinding(() -> {
                        Point2D targetLocation = missile1.localToParent(missile1.getCenterX(), missile1.getCenterY());
                        Point2D bulletLocation = bullet.localToParent(weaponEnd);
                        return (targetLocation.distance(bulletLocation) < missileRadius + bulletRadius) ;
                    }, bullet.translateXProperty(), bullet.translateYProperty());
                    shotMissle1.addListener((obs, wasHit, isNowHit) -> {
                        if (isNowHit) {
                            System.out.println(missileCount());
                            missileHitCounter ++;
                            counter.setText(missileCount());
                            battle.getChildren().remove(bullet); //bullet out
                            battle.getChildren().remove(missile1);
                        }
                    });
                    break;
                case ESCAPE:
                    if(missle1Path.getStatus() == Status.RUNNING) { missle1Path.pause(); } else { missle1Path.play(); }
                    if(missle2Path.getStatus() == Status.RUNNING) { missle2Path.pause(); } else { missle2Path.play(); }
                    if(missle3Path.getStatus() == Status.RUNNING) { missle3Path.pause(); } else { missle3Path.play(); }
                    break;
                case G:
                    if (ballistic1.getStroke() == Color.WHITE) { 
                        Random rand = new Random();
                        ballistic1.setStroke(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                        ballistic2.setStroke(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                        ballistic3.setStroke(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                    }
                    else {
                        ballistic1.setStroke(Color.WHITE);
                        ballistic2.setStroke(Color.WHITE);
                        ballistic3.setStroke(Color.WHITE);
                    }
                    break;
                default:
                    break;
            }
        });
        
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Defender: UP/DOWN -> Rotate Weapon ; SPACE: Shot ; ESCAPE: Pause/Resume : G : Show Ballistics");
        stage.setScene(scene);
        stage.show();
    }

    private String missileCount () {
        return "Statistic: " + missileFiredCounter +" Missle fired; " + missileHitCounter + " Missle hit ";
    }

    public static void main(String[] args) {
        launch(args);
    }
}