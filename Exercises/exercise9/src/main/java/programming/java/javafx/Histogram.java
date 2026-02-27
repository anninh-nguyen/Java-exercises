package programming.java.javafx;

import java.util.ArrayList;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Histogram extends Pane {

    // scale up the chart 2 times -> every one appearance draw 2 pixel
    Integer histogramScaleRatio = 2;
    Integer histogramBarWidth = 20;
    ArrayList<Entry<String, Integer>> labelDictionary = new ArrayList<>();
    Integer maxCount;
    GridPane histogramBox = new GridPane(10, 10);

    public Histogram() {
    }

    public Integer getHistogramScaleRatio() {
        return histogramScaleRatio;
    }
    public void setHistogramScaleRatio(Integer histogramScaleRatio) {
        this.histogramScaleRatio = histogramScaleRatio;
    }

    public Integer getHistogramBarWidth() {
        return histogramBarWidth;
    }
    public void setHistogramBarWidth(Integer histogramBarWidth) {
        this.histogramBarWidth = histogramBarWidth;
    }

    public GridPane getHistogramBox() {
        return histogramBox;
    }

    public void buildHistogram(ArrayList<Entry<String, Integer>> labelDictionary) {

        this.labelDictionary = labelDictionary;

        // Clear previous histogram
        histogramBox.getChildren().clear();

        this.labelDictionary = labelDictionary;
        maxCount = labelDictionary.stream().mapToInt(Entry::getValue).max().orElse(1);

        int index = 0;
        for (Entry<String, Integer> entry : labelDictionary) {
            Label characterText = new Label(entry.getKey());
            Label characterCount = new Label(entry.getValue().toString());
            Rectangle histogramBar = new Rectangle();

            histogramBar.setWidth(histogramBarWidth);
            histogramBar.setFill(setColor(entry.getValue()));
            histogramBar.setHeight(((double) entry.getValue()) * histogramScaleRatio); 

            histogramBox.add(characterCount, index, 0);
            GridPane.setHalignment(characterCount,  HPos.CENTER);

            histogramBox.add(histogramBar, index, 1);
            GridPane.setValignment(histogramBar, VPos.BOTTOM);

            histogramBox.add(characterText, index, 2);
            GridPane.setHalignment(characterText,  HPos.CENTER);
            
            index++;
        }
    }

    private Color setColor(Integer count) {
        double ratio = (double) count / maxCount;
        
        if (ratio < 0.2) return Color.DARKRED;
        if (ratio < 0.4) return Color.DARKTURQUOISE;
        if (ratio < 0.6) return Color.LIGHTSALMON;
        if (ratio < 0.8) return Color.SIENNA;

        return Color.SEAGREEN;
    }
}
