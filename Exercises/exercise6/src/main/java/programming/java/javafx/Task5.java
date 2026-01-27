package programming.java.javafx;

import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task5 extends Application {

    double boundaryWidth = 1200;
    double boundaryHeigh = 800;
    double margin = 50.0;
    
    Ellipse ellipse = new Ellipse();
    Circle orbiter1 = new Circle();

    Polygon rhombus = new Polygon();
    Circle orbiter2 = new Circle();

    Text txColision = new Text();
    int colisionTime = 0;

    @Override
    public void start(Stage primaryStage) {
        ellipse.setStroke(Color.AQUA); ellipse.setFill(Color.rgb(0, 0, 0, 0));

        DoubleProperty xValue = new SimpleDoubleProperty();
        xValue.bind(orbiter1.centerXProperty());
        xValue.addListener(ov -> { orbiterColision(); });

        ellipse.setCenterX(boundaryWidth/2); ellipse.setCenterY(boundaryHeigh/2); ellipse.setRadiusX(boundaryWidth/2 - margin*3); ellipse.setRadiusY(boundaryHeigh/2 - margin*2);
        orbiter1.setFill(Color.LIGHTCORAL); orbiter1.setRadius(margin);
        
        PathTransition ellipseTransition = new PathTransition(Duration.seconds(3.0),ellipse, orbiter1);
        ellipseTransition.setCycleCount(Timeline.INDEFINITE);
        ellipseTransition.play();
        
        rhombus.getPoints().addAll(new Double[]{ boundaryWidth/2.0, 0.0, boundaryWidth, boundaryHeigh/2, boundaryWidth/2, boundaryHeigh, 0.0, boundaryHeigh/2});
        rhombus.setStroke(Color.AQUAMARINE); rhombus.setFill(Color.rgb(0, 0, 0, 0));
        orbiter2.setFill(Color.BURLYWOOD); orbiter2.setRadius(margin);

        PathTransition rhombusTransition = new PathTransition(Duration.seconds(13.0), rhombus, orbiter2);
        rhombusTransition.setCycleCount(Timeline.INDEFINITE);
        rhombusTransition.play();

        Pane pane = new Pane();
        pane.getChildren().addAll(rhombus, orbiter2, ellipse, orbiter1);
        pane.setOnMouseClicked(mouseClickEvent -> {
            if (mouseClickEvent.getButton() == MouseButton.PRIMARY) {
                if(rhombusTransition.getStatus() == Status.RUNNING) { rhombusTransition.pause(); }
                else { rhombusTransition.play();}
            }
            else if (mouseClickEvent.getButton() == MouseButton.SECONDARY) {
                if (ellipseTransition.getStatus() == Status.RUNNING) { ellipseTransition.pause(); }
                else { ellipseTransition.play();}
            }
        });

        Text ellipseSliderText = new Text("Ellipse slider");
        Slider ellipseSlider = new Slider(1, 10, 3);
        ellipseSlider.setOnMouseDragged(mouseDraged -> {
            ellipseTransition.stop();
            ellipseTransition.setDuration(Duration.seconds(ellipseSlider.getValue()));
            ellipseTransition.play();
        });
        Button playEllipse = new Button("Pause");
        playEllipse.setOnAction(pressEvent -> {
            if(ellipseTransition.getStatus() == Status.RUNNING) { 
                ellipseTransition.pause(); 
                playEllipse.setText("Play"); 
            }
            else { 
                ellipseTransition.play();
                playEllipse.setText("Pause");
            }
        });

        Slider rhombusSlider = new Slider(1, 10, 7);
        rhombusSlider.setOnMouseDragged(mouseDraged -> {
            rhombusTransition.stop();
            rhombusTransition.setDuration(Duration.seconds(rhombusSlider.getValue()));
            rhombusTransition.play();
        });
        Text rhombusSliderText = new Text("Rhombus slider");
        Button playRhombus = new Button("Pause");
        playRhombus.setOnAction(pressEvent -> {
            if(rhombusTransition.getStatus() == Status.RUNNING) { 
                rhombusTransition.pause(); 
                playRhombus.setText("Play"); 
            }
            else { 
                rhombusTransition.play();
                playRhombus.setText("Pause");
            }
        });

        bindMoving();

        GridPane controlBox = new GridPane();
        controlBox.setPadding(new Insets(20));
        controlBox.setHgap(10);
        controlBox.setVgap(10);
        controlBox.add(ellipseSliderText, 0, 0);
        controlBox.add(ellipseSlider, 0, 1);
        controlBox.add(playEllipse, 1, 1);

        controlBox.add(rhombusSliderText, 2, 0);
        controlBox.add(rhombusSlider, 2, 1);
        controlBox.add(playRhombus, 3, 1);

        controlBox.add(txColision, 4, 1);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(pane, controlBox);

        Scene scene = new Scene(vbox,boundaryWidth,boundaryHeigh);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("PRIMARY CLICK: Play/Pause orbiter #1 ;  SECONDARY CLICK: Play/Pause orbiter #2");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean orbiterColision(){
        var distance = Math.sqrt(Math.pow(orbiter1.getCenterX() - orbiter2.getCenterX(),2) + Math.pow(orbiter1.getCenterY() - orbiter2.getCenterY(),2));
        return distance <= 2*margin;
    }

    private void bindMoving() {
        BooleanBinding colise = Bindings.createBooleanBinding(() -> {
            Point2D orbiter1Location = orbiter2.localToParent(orbiter1.getCenterX(), orbiter1.getCenterY());
            Point2D orbiter2Location = orbiter1.localToParent(orbiter2.getCenterX(), orbiter2.getCenterY());
            return (orbiter2Location.distance(orbiter1Location) <= 2*margin) ;
        }, orbiter1.translateXProperty(), orbiter1.translateYProperty(), 
           orbiter2.translateXProperty(), orbiter2.translateYProperty());

        colise.addListener((obs, wasColise, isNowColise) -> {
            colisionTime++;
            txColision.setText("Two orbiter colised " + colisionTime + " time(s)");
        });
    }
}