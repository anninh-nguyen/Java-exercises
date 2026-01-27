package programming.java.javafx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Code related to Timeine is suggested by ChatGPT-4o
 */

public class Task7 extends Application {
    private int screenWidth = 400, screenHeight = 600;
    private int birdRadius = 15;
    private int pipeWidth = 60, minPipeGap = 50, maxPipeGap = 100;
    
    private Circle bird;
    private ParallelTransition gameLoop;
    private List<Rectangle> pipes = new ArrayList<>();
    private Random random = new Random();
    private boolean gameOver = false;
    
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        
        bird = new Circle(screenWidth / 4, screenHeight / 2, birdRadius, Color.YELLOWGREEN);
        pane.getChildren().add(bird);
        
        Timeline gravityTimeline; 
        gravityTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            bird.setTranslateY(bird.getTranslateY() + 1);                           // bird falls down
            checkCollision();
        }));
        gravityTimeline.setCycleCount(Animation.INDEFINITE);
        
        Timeline pipeTimeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> spawnPipes(pane)));
        pipeTimeline.setCycleCount(Animation.INDEFINITE);
        
        gameLoop = new ParallelTransition(gravityTimeline, pipeTimeline);
        gameLoop.play();

        
        Scene scene = new Scene(pane, screenWidth, screenHeight);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                bird.setTranslateY(bird.getTranslateY() - 30);                      // bird flies up
            }
        });
        
        stage.setScene(scene);
        stage.setTitle("Flappy Bird");
        stage.show();
    }
    
    private void spawnPipes(Pane pane) {
        if (gameOver) {
            bird.setFill(Color.DARKORANGE);
            return;
        }
        
        int pipeGap = Math.max(minPipeGap, random.nextInt(maxPipeGap));
        int topPipeHeight = Math.max(minPipeGap, random.nextInt(screenHeight - pipeGap));  // min & max height of top pipe
        var topPipe = new Rectangle(screenWidth, 0, pipeWidth, topPipeHeight);
        var bottomPipe = new Rectangle(screenWidth, topPipeHeight + pipeGap, pipeWidth, screenHeight - topPipeHeight - pipeGap);
        
        pipes.add(topPipe);
        pipes.add(bottomPipe);
        pane.getChildren().addAll(topPipe, bottomPipe);
        
        topPipe.setFill(Color.GREEN);
        bottomPipe.setFill(Color.GREEN);
        
        PathTransition transitionTop = new PathTransition(Duration.seconds(4), new Line(screenWidth, 0, -pipeWidth, 0), topPipe);
        PathTransition transitionBottom = new PathTransition(Duration.seconds(4), new Line(screenWidth, screenHeight, -pipeWidth, screenHeight), bottomPipe);
        
        transitionTop.setOnFinished(e -> pane.getChildren().remove(topPipe));
        transitionBottom.setOnFinished(e -> pane.getChildren().remove(bottomPipe));
        
        transitionTop.play();
        transitionBottom.play();
    }
    
    private void checkCollision() {
        pipes.forEach(pipe -> {
            if (bird.getBoundsInParent().intersects(pipe.getBoundsInParent())) {
                gameOver = true;
                gameLoop.stop();
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
