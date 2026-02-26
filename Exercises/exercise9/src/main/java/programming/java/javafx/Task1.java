package programming.java.javafx;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Task1 extends Application {

    String textContent = new String();
    ArrayList<Entry<String, Integer>> charactersDict = new ArrayList<>();
    VBox root = new VBox(5);
    Integer maxCount;
    // scale up the chart 100 times -> every one appearance draw 100 pixel
    Integer histogramScaleRatio = 100;
    Integer histogramBarWidth = 20;
    
    GridPane histogramBox = new GridPane(10, 10);

    @Override
    public void start(Stage stage) throws Exception {
        // Read the text file
        File myObj = new File("text.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine())
        {
            String data = myReader.nextLine();
            textContent += data;
        }
        myReader.close();

        buildCharacterDict();
        buildOptionsBox();
        root.getChildren().add(histogramBox);
        buildOriginalTextBox();

        // main histogram box
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 720 , 430);
        stage.setScene(scene);
        stage.setTitle("Text to Histogram");
        stage.show();
    }

    private void buildCharacterDict () {
        // Read and count individual characters from the document
        for (char c : textContent.toCharArray()) {
            if (!Character.isWhitespace(c) && !Character.isDigit(c) && Character.isLetter(c)) {
                if (charactersDict.stream().noneMatch(entry -> entry.getKey().equals(String.valueOf(c).toUpperCase()))) {
                    charactersDict.add(new AbstractMap.SimpleEntry<>(String.valueOf(c).toUpperCase(), 1));
                } else {
                    for (Entry<String, Integer> entry : charactersDict) {
                        if (entry.getKey().equals(String.valueOf(c).toUpperCase())) {
                            entry.setValue(entry.getValue() + 1);
                            break;
                        }
                    }
                }
            }
        }
        maxCount = charactersDict.stream().mapToInt(Entry::getValue).max().orElse(1);
    }

    private void sortDictionary(boolean isSortedByKey, boolean isAscending) {
        if (isSortedByKey) {
            charactersDict.sort((a, b) -> isAscending ? a.getKey().compareTo(b.getKey()) : b.getKey().compareTo(a.getKey()));
        } else {
            charactersDict.sort((a, b) -> isAscending ? a.getValue().compareTo(b.getValue()) : b.getValue().compareTo(a.getValue()));
        }
    }

    private void buildOptionsBox() {
        Label optionsLabel = new Label("Sorting Options:");
        root.getChildren().add(optionsLabel);

        HBox optionsBox = new HBox(20);
        ToggleGroup sortByGroup = new ToggleGroup();
        RadioButton sortByKey = new RadioButton("Sort by Key");
        sortByKey.setSelected(true);
        sortByKey.setToggleGroup(sortByGroup);
        RadioButton sortByValue = new RadioButton("Sort by Value");
        sortByValue.setToggleGroup(sortByGroup);

        ToggleGroup orderGroup = new ToggleGroup();
        RadioButton ascendingOrder = new RadioButton("Ascending");
        ascendingOrder.setSelected(true);
        ascendingOrder.setToggleGroup(orderGroup);
        RadioButton descendingOrder = new RadioButton("Descending");
        descendingOrder.setToggleGroup(orderGroup);

        optionsBox.getChildren().addAll(sortByKey, sortByValue, ascendingOrder, descendingOrder);
        optionsBox.setPadding(new Insets(0,0,10,0));
        root.getChildren().add(optionsBox);

        // Add listeners to the radio buttons
        sortByKey.setOnAction(e -> {
            sortDictionary(true, ascendingOrder.isSelected());
            buildHistogram();
        });
        
        sortByValue.setOnAction(e -> {
            sortDictionary(false, ascendingOrder.isSelected());
            buildHistogram();
        });

        ascendingOrder.setOnAction(e -> {
            if (sortByKey.isSelected()) {
                sortDictionary(true, true);
            } else {
                sortDictionary(false, true);
            }
            buildHistogram();
        });

        descendingOrder.setOnAction(e -> {
            if (sortByKey.isSelected()) {
                sortDictionary(true, false);
            } else {
                sortDictionary(false, false);
            }
            buildHistogram();
        });
    }

    private void buildHistogram() {

        // Clear previous histogram
        histogramBox.getChildren().clear();

        int index = 0;
        for (Entry<String, Integer> entry : charactersDict) {
            Label characterText = new Label(entry.getKey());
            Label characterCount = new Label(entry.getValue().toString());
            Rectangle histogramBar = new Rectangle();

            histogramBar.setWidth(histogramBarWidth);
            histogramBar.setFill(setColor(entry.getValue()));
            histogramBar.setHeight(((double) entry.getValue() / maxCount) * histogramScaleRatio); 
            
            histogramBox.add(characterCount, index, 0);
            GridPane.setHalignment(characterCount,  HPos.CENTER);

            histogramBox.add(histogramBar, index, 1);
            GridPane.setValignment(histogramBar, VPos.BOTTOM);

            histogramBox.add(characterText, index, 2);
            GridPane.setHalignment(characterText,  HPos.CENTER);
            
            index++;
        }
    }

    private void buildOriginalTextBox() {
        /// Original text content
        Label originalTextLabel = new Label("Orignal file's content:");
        TextArea originalContent = new TextArea(textContent);
        originalContent.setMaxWidth(700);
        originalContent.setMaxHeight(380);
        originalContent.setWrapText(true);
        root.getChildren().add(originalTextLabel);
        root.getChildren().add(originalContent);
    }

    private Color setColor(Integer count) {
        double ratio = (double) count / maxCount;
        
        if (ratio < 0.2) return Color.DARKRED;
        if (ratio < 0.4) return Color.DARKTURQUOISE;
        if (ratio < 0.6) return Color.LIGHTSALMON;
        if (ratio < 0.8) return Color.SIENNA;

        return Color.SEAGREEN;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
