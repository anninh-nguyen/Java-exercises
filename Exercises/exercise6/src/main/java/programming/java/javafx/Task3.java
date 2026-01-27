package programming.java.javafx;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task3 extends Application {

    // A pair of image clicked
    ImageView clickedIV1, clickedIV2;
    boolean isFirstImageClicked = false;

    ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
    ArrayList<Image> images = new ArrayList<Image>();
    boolean isMatched = false;
    GridPane imageGrid = new GridPane();
    Text txResult = new Text();

    int imageSize = 120;
    Image cover;
    Image coverOver;
    int imagesNeeded = 0;
    int pairMatches = 0;

    long startingTime; // record matching time spent

    @SuppressWarnings("rawtypes")
    @Override
    public void start(Stage stage) throws Exception {
                            
        imageGrid.setHgap(10);
        imageGrid.setVgap(10);
        imageGrid.setPadding(new Insets(20));

        Text txRow = new Text("Rows");
        Spinner rowSpinner = new Spinner(2, 5, 5);
        
        Text txCol = new Text("Columns");
        Spinner colSpinner = new Spinner(2, 6, 6);

        Spinner imgSizeSpinner = new Spinner(50, 400, 120, 10); //min, max, init, step
        Text txSize = new Text("Image Size (px)");
        
        Button btShuffle = new Button("SHUFFLE IMAGES");
        btShuffle.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                isMatched = false; // reset last result
                ScatterImages((Integer) rowSpinner.getValue(), (Integer) colSpinner.getValue(), (Integer)imgSizeSpinner.getValue(), true);
            }
        });

        Button btReset = new Button("NO SHUFFLE IMAGES");
        btReset.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent event) {
                isMatched = false; // reset last result
                ScatterImages((Integer) rowSpinner.getValue(), (Integer) colSpinner.getValue(), (Integer)imgSizeSpinner.getValue(), false);
            }
        });
        
        GridPane controlBox = new GridPane();
        controlBox.setHgap(10);
        controlBox.setVgap(10);
        controlBox.setPadding(new Insets(20));
        
        controlBox.add(txRow, 0, 0);
        controlBox.add(rowSpinner, 0, 1);
        controlBox.add(txCol, 1, 0);
        controlBox.add(colSpinner, 1, 1);
        controlBox.add(txSize, 2, 0);
        controlBox.add(imgSizeSpinner, 2, 1);
        controlBox.add(txResult, 3, 0); GridPane.setColumnSpan(txResult, 3);
        controlBox.add(btShuffle, 3, 1);
        controlBox.add(btReset, 4, 1);
        
        ScatterImages((Integer) rowSpinner.getValue(),(Integer) colSpinner.getValue(), (Integer)imgSizeSpinner.getValue(), true);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(imageGrid, controlBox);
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Matching game");
        stage.show();
    }

    private void ScatterImages(int rowCount, int columnCount, int  imageSize, boolean isShuffle) {
        if ((rowCount * columnCount)%2 != 0) { 
            txResult.setText("The number of image must should be even");
            return;
        }

        cover = new Image("Cover.png", imageSize, imageSize, true, true);
        coverOver = new Image("Cover_over.png", imageSize, imageSize, true, true);

        // dispose previouse Images and ImageView
        images.clear();
        imageViews.clear();
        imageGrid.getChildren().clear();

        imagesNeeded = (rowCount * columnCount)/2;
        txResult.setText(Integer.toString(imagesNeeded) + " PAIRS TO MATCHING");
        pairMatches = 0;
        
        for (int i = 0; i < imagesNeeded; i++) {
            Image image = new Image(Integer.toString(i) + ".png", imageSize, imageSize, true, true);
            images.add(image); images.add(image); // add twice for a pair
        }
        
        if (isShuffle) { Collections.shuffle(images); }

        int imageViewPosition = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                ImageView imageView = new ImageView();
                imageViews.add(imageView);
                imageView.setId(Integer.toString(imageViewPosition));
                imageView.setImage(cover);

                imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent arg0) {
                        // image already opened, do nothing
                        if (imageView.getImage() != cover) return;

                        // image at position overing
                        imageView.setImage(coverOver);

                    }
                });

                imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent arg0) {
                        // image already opened, do nothing
                        if (imageView.getImage() != coverOver) return;

                        // image at position overing
                        imageView.setImage(cover);
                    }
                });

                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    // Replace hidden image and make comparision
                    public void handle(MouseEvent arg0) {
                        // image already opened, do nothing
                        if (imageView.getImage() != coverOver); // || imageView.getImage() != coverOver) return;

                        // image at position cliked
                        Image clickedImage = images.get(Integer.parseInt(imageView.getId()));
                        imageView.setImage(clickedImage);
                        
                        // the first-of-a-pair click
                        if(!isFirstImageClicked) {
                            // recover the last two mismatched image
                            if (!isMatched && clickedIV1 != null & clickedIV2 != null){
                                clickedIV1.setImage(cover);
                                clickedIV2.setImage(cover);
                            }

                            isFirstImageClicked = true;
                            clickedIV1 = imageView;
                        }
                        // the second-of-a-pair click
                        else {
                            isFirstImageClicked = false;
                            clickedIV2 = imageView;

                            // matched
                            if (clickedIV1.getImage() == clickedIV2.getImage()){
                                isMatched = true;
                                pairMatches++;
                                txResult.setText(Integer.toString(pairMatches) + " / " + Integer.toString(imagesNeeded) + " PAIRS MATCHED");
                            }
                            // not matched, load two covers and reset
                            else {
                                txResult.setText("MISMATCHED");
                                isMatched = false;
                            }
                        }

                        if (pairMatches == imagesNeeded) {
                            long timeElapsed = Instant.now().toEpochMilli() - startingTime;
                            txResult.setText("NICE TRIED! YOU MATCHED ALL PAIRS IN " + timeElapsed/1000 + " SECONDS");
                        }
                    }
                });

                imageGrid.add(imageView, j, i);
                imageViewPosition++;
            }
        }
        
        startingTime = Instant.now().toEpochMilli(); // start counting time
    }

    public static void main(String[] args) {
        launch(args);
    }
}
