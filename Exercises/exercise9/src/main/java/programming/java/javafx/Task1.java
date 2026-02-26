package programming.java.javafx;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Task1 extends Application {

    String textContent = new String();
    ArrayList<Entry<String, Integer>> charactersDict = new ArrayList<>();
    VBox root = new VBox(5);

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
        buildHistogram();

        Label originalTextLabel = new Label("Orignal file's content:");
        TextArea originalContent = new TextArea(textContent);
        originalContent.setMaxWidth(1000);
        originalContent.setMaxHeight(700);
        originalContent.setWrapText(true);

        root.getChildren().add(originalTextLabel);
        root.getChildren().add(originalContent);

        Scene scene = new Scene(root, 1080 , 720);
        stage.setScene(scene);
        stage.setTitle("Text to Histogram");
        stage.show();
    }

    private void buildCharacterDict () {
        // Read and count single characters from the document
        for (char c : textContent.toCharArray()) {
            if (!Character.isWhitespace(c) && !isNumeric(String.valueOf(c))) {
                if (charactersDict.stream().noneMatch(entry -> entry.getKey().equals(String.valueOf(c).toUpperCase()))) {
                    charactersDict.add(new AbstractMap.SimpleEntry<>(String.valueOf(c).toUpperCase(), 1));
                } else {
                    for (Entry<String, Integer> entry : charactersDict) {
                        if (entry.getKey().equals(String.valueOf(c))) {
                            entry.setValue(entry.getValue() + 1);
                            break;
                        }
                    }
                }
            }
        }
        charactersDict.sort((a, b) -> a.getKey().compareTo(b.getKey()));
    }

    private void buildHistogram() {
        // scale up the chart 100 times -> every one appearance draw 100 pixel
        Integer histogramScaleRatio = 100;
        Integer histogramBarWidth = 20;

        Integer maxCount = charactersDict.stream().mapToInt(Entry::getValue).max().orElse(1);

        GridPane histogramBox = new GridPane(10, 10);
        histogramBox.setPadding(new Insets(20));

        int index = 0;
        for (Entry<String, Integer> entry : charactersDict) {
            Label characterText = new Label(entry.getKey());
            Label characterCount = new Label(entry.getValue().toString());
            Rectangle histogramBar = new Rectangle();
            histogramBar.setWidth(histogramBarWidth);
            histogramBar.setFill(Color.BLUE);
            histogramBar.setHeight(((double) entry.getValue() / maxCount) * histogramScaleRatio); 
            
            histogramBox.add(characterCount, index, 0);
            histogramBox.add(histogramBar, index, 1);

            GridPane.setValignment(histogramBar, VPos.BOTTOM);
            histogramBox.add(characterText, index, 2);
            
            index++;
        }

        root.getChildren().add(histogramBox);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
