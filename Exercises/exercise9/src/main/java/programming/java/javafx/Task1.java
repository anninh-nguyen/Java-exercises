package programming.java.javafx;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Task1 extends Application {

    String textContent = new String();
    ArrayList<Entry<String, Integer>> labelDictionary = new ArrayList<>();
    Histogram histogram = new Histogram();
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

        buildLabelDictionary();
        sortDictionary(true, true);
        histogram.buildHistogram(labelDictionary);
        GridPane histogramBox = histogram.getHistogramBox();

        // main histogram box
        root.setPadding(new Insets(20));
        root.getChildren().add(histogramBox);

        buildOptionsBox();
        buildContentGrid();

        Scene scene = new Scene(root, 720 , 600);
        stage.setScene(scene);
        stage.setTitle("Text to Histogram");
        stage.show();
    }

    private void buildLabelDictionary () {
        labelDictionary.clear();

        // Read and count individual characters from the document
        for (char c : textContent.toCharArray()) {
            if (!Character.isWhitespace(c) && !Character.isDigit(c) && Character.isLetter(c)) {
                if (labelDictionary.stream().noneMatch(entry -> entry.getKey().equals(String.valueOf(c).toUpperCase()))) {
                    labelDictionary.add(new AbstractMap.SimpleEntry<>(String.valueOf(c).toUpperCase(), 1));
                } else {
                    for (Entry<String, Integer> entry : labelDictionary) {
                        if (entry.getKey().equals(String.valueOf(c).toUpperCase())) {
                            entry.setValue(entry.getValue() + 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void sortDictionary(boolean isSortedByKey, boolean isAscending) {
        if (isSortedByKey) {
            labelDictionary.sort((a, b) -> isAscending ? a.getKey().compareTo(b.getKey()) : b.getKey().compareTo(a.getKey()));
        } else {
            labelDictionary.sort((a, b) -> isAscending ? a.getValue().compareTo(b.getValue()) : b.getValue().compareTo(a.getValue()));
        }
    }

    private void buildOptionsBox() {
        GridPane optionsBox = new GridPane(20, 5);

        Label optionsLabel = new Label("Sorting Options:");
        optionsBox.add(optionsLabel, 0, 0);

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

        HBox optionsHBox = new HBox(10);
        optionsHBox.getChildren().addAll(sortByKey, sortByValue, ascendingOrder, descendingOrder);
        optionsBox.add(optionsHBox, 0, 1);
        optionsBox.setPadding(new Insets(10,0,10,0));
        root.getChildren().add(optionsBox);

        // Add listeners to the radio buttons
        sortByKey.setOnAction(e -> {
            sortDictionary(true, ascendingOrder.isSelected());
            histogram.buildHistogram(labelDictionary);
        });
        
        sortByValue.setOnAction(e -> {
            sortDictionary(false, ascendingOrder.isSelected());
            histogram.buildHistogram(labelDictionary);
        });

        ascendingOrder.setOnAction(e -> {
            if (sortByKey.isSelected()) {
                sortDictionary(true, true);
            } else {
                sortDictionary(false, true);
            }
            histogram.buildHistogram(labelDictionary);
        });

        descendingOrder.setOnAction(e -> {
            if (sortByKey.isSelected()) {
                sortDictionary(true, false);
            } else {
                sortDictionary(false, false);
            }
            histogram.buildHistogram(labelDictionary);
        });

        Label scaleRatioLabel = new Label("Scale Ratio:");
        optionsBox.add(scaleRatioLabel, 1, 0);
        Slider scaleRatioSlider = new Slider(1, 10, histogram.getHistogramScaleRatio());
        scaleRatioSlider.setShowTickLabels(true);
        scaleRatioSlider.setShowTickMarks(true);
        scaleRatioSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            histogram.setHistogramScaleRatio(newValue.intValue());
            histogram.buildHistogram(labelDictionary);
        });
        scaleRatioSlider.setPrefWidth(120);
        optionsBox.add(scaleRatioSlider, 1, 1);

        Label barWidthLabel = new Label("Bar Width:");
        optionsBox.add(barWidthLabel, 2, 0);
        Slider barWidthSlider = new Slider(10, 100, histogram.getHistogramBarWidth());
        barWidthSlider.setShowTickLabels(true);
        barWidthSlider.setShowTickMarks(true);
        barWidthSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            histogram.setHistogramBarWidth(newValue.intValue());
            histogram.buildHistogram(labelDictionary);
        });
        barWidthSlider.setPrefWidth(120);
        optionsBox.add(barWidthSlider, 2, 1);
    }

    private void buildContentGrid() {
        /// Original text content
        GridPane contentGrid = new GridPane();
        contentGrid.setPrefWidth(700);

        Label originalTextLabel = new Label("File's content: ");
        contentGrid.add(originalTextLabel, 0, 0);
        TextArea originalContent = new TextArea(textContent);

        Label fileNameLabel = new Label("text.txt");
        contentGrid.add(fileNameLabel, 1, 0);
        contentGrid.setHalignment(fileNameLabel, HPos.LEFT);

        Button selectFileButton = new Button("Select .txt File");
        selectFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                fileNameLabel.setText(selectedFile.getName());

                textContent = "";
                try (Scanner scanner = new Scanner(selectedFile)) {
                    while (scanner.hasNextLine()) {
                        textContent += scanner.nextLine();
                    }
                    originalContent.setText(textContent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                buildLabelDictionary();
                sortDictionary(true, true);
                histogram.buildHistogram(labelDictionary);
            }
        });
        contentGrid.add(selectFileButton, 2, 0);
        contentGrid.setHalignment(selectFileButton, HPos.RIGHT);

        originalContent.setMaxWidth(700);
        originalContent.setMaxHeight(480);
        originalContent.setWrapText(true);
        contentGrid.add(originalContent, 0, 1);
        GridPane.setColumnSpan(originalContent, 3);

        root.getChildren().add(contentGrid);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
