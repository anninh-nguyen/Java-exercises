package programming.java.javafx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class Task2 extends Application {
    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        stage.setTitle("Character Histogram");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        HBox fileBox = new HBox(10);
        TextField filePath = new TextField();
        filePath.setPrefWidth(300);
        Button browseBtn = new Button("Browse");
        fileBox.getChildren().addAll(new Label("File:"), filePath, browseBtn);

        ComboBox<String> sortBy = new ComboBox<>();
        sortBy.getItems().addAll("Character", "Frequency");
        sortBy.setValue("Character");

        ComboBox<String> sortOrder = new ComboBox<>();
        sortOrder.getItems().addAll("Ascending", "Descending");
        sortOrder.setValue("Ascending");

        Button loadBtn = new Button("Load & Display");
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        chart.setTitle("Character Histogram");

        loadBtn.setOnAction(e -> {
            try {
                Map<Character, Integer> histogram = new HashMap<>();
                String content = new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get(filePath.getText())));
                
                for (char c : content.toLowerCase().toCharArray()) {
                    if (Character.isLetter(c)) {
                        histogram.put(c, histogram.getOrDefault(c, 0) + 1);
                    }
                }
                
                var stream = histogram.entrySet().stream();
                if ("Character".equals(sortBy.getValue())) {
                    stream = stream.sorted(Map.Entry.comparingByKey());
                } else {
                    stream = stream.sorted(Map.Entry.comparingByValue());
                }
                
                if ("Descending".equals(sortOrder.getValue())) {
                    stream = stream.sorted(Collections.reverseOrder());
                }
                
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                stream.forEach(d -> series.getData().add(
                    new XYChart.Data<>(String.valueOf(d.getKey()), d.getValue())));
                
                chart.getData().clear();
                chart.getData().add(series);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        browseBtn.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            java.io.File file = fc.showOpenDialog(stage);
            if (file != null) filePath.setText(file.getAbsolutePath());
        });

        root.getChildren().addAll(fileBox, 
            new HBox(10, new Label("Sort by:"), sortBy, sortOrder), 
            loadBtn, chart);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        stage.setScene(new Scene(scrollPane, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
