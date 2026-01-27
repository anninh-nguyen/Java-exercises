package programming.java.javafx;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class Task2 extends Application {
    
    private ComboBox mealCategory = new ComboBox<>();
    private ListView dishesList = new ListView<>();
    private TextArea dishIngredentsArea = new TextArea("Dishes ingredents show here");
    ArrayList<Entry<String, Entry<String, List<String>>>> ingredents;
    public void start(Stage stage) {

        ingredents = prepareData();
        
        Text mealCategoryCaption = new Text("Select your cuisine style");
        mealCategory.setOnAction(e -> { onSelectCuisine(); });
        
        List<String> cuisineList = new ArrayList<String>();
        ingredents.forEach(t-> cuisineList.add(t.getKey()));
        var distintCuisineList = cuisineList.stream().distinct().collect(Collectors.toList());
        distintCuisineList.forEach(t -> mealCategory.getItems().add(t));
        VBox mealCategorySection = new VBox();
        mealCategorySection.setSpacing(30);
        mealCategorySection.getChildren().addAll(mealCategoryCaption, mealCategory);

        Text dishesCaption = new Text("Select your dishes");
        dishesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        dishesList.setOnMouseClicked(e -> { onSelectDish(); });
        VBox dishesSection = new VBox();
        dishesSection.setSpacing(30);
        dishesSection.getChildren().addAll(dishesCaption, dishesList);

        Text dishesIngredentsCaption = new Text("Ingredents in your selected dish(es)");
        dishIngredentsArea.setPrefRowCount(20);
        VBox ingredentSection = new VBox();
        ingredentSection.setSpacing(30);
        ingredentSection.getChildren().addAll(dishesIngredentsCaption, dishIngredentsArea);

        HBox main = new HBox();
        main.setSpacing(30);
        main.setPadding(new Insets(30));
        main.getChildren().addAll(mealCategorySection, dishesSection, ingredentSection);

        Text copyRightText = new Text("Sourse: Top 69 Finnish Foods - TasteAtlas.com");
        Hyperlink copyrightLink = new Hyperlink("https://www.tasteatlas.com/best-rated-dishes-in-finland", copyRightText);

        VBox root = new VBox();
        root.setSpacing(30);
        root.getChildren().addAll(main, copyRightText, copyrightLink);

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("International Restaurant Menu");
        stage.show();
    }

    private ArrayList<Entry<String, Entry<String,List<String>>>> prepareData () {
        var ingredents = new ArrayList<Entry<String, Entry<String,List<String>>>> ();
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Finnish",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Loimulohi",
                List.of("Salmon", "Trout", "Lemon Juice", "Black Pepper", "Salt")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Finnish",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Finnish fish soup (Kalakeitto)",
                List.of("Fish", "Potatoes", "Onion", "Bay Leaves", "Allspice", "Dill")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Finnish",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Salmon soup (Lohikeitto)",
                List.of("Salmon", "Potatoes", "Carrot", "Leek", "Butter", "Dill", "Heavy Cream", "Fish Stock", "Black Pepper",  "Salt")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Finnish",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Finnish blueberry pie (Mustikkapiirakka)",
                List.of("Rye flour", "Wheat Flour", "Bilberries", "Blueberries", "Butter", "Eggs", "Cardamom", "Cinnamon", "Vanilla", "Baking Powder", "Sour Cream", "Sugar")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "German",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Pretzel (Brezel)",
                List.of("Wheat Flour","Yeast", "Baking Soda", "Butter", "Sugar", "Salt")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "German",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Bratwurst",
                List.of("Ground Pork", "Marjoram", "Mace", "Coriander Seeds", "Black Pepper", "Salt")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "German",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Schwarzwälder Kirschtorte",
                List.of("Wheat Flour", "Eggs", "Baking Powder", "Cocoa Powder", "Chocolate", "Sour Cherries", "Cherries", "Redcurrant Jam")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Japanese",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Ramen",
                List.of("Beef", "Potatoes", "Onion", "Garlic", "Salt")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Japanese",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Sushi",
                List.of("Ramen Noodles", "Pork", "Root Vegetable", "Mushrooms", "Chashu")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Japanese",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Tonkatsu",
                List.of("Pork", "Flour", "Breadcrumbs", "Eggs", "Oil")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Chinese",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Jiaozi",
                List.of("Wheat Flour", "Ground Pork", "Napa cabbage", "Bamboo shoots","Scallions", "Ginger", "Garlic")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Chinese",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Wonton",
                List.of("Wonton wrappers", "Shrimps", "Ground Pork", "Ginger", "Scallions", "Onion", "Soy sauce", "Sesame Oil")
            )
        ));
        ingredents.add(new AbstractMap.SimpleEntry<String, Entry<String,List<String>>>(
            "Chinese",
            new AbstractMap.SimpleEntry<String, List<String>>(
                "Chow mein",
                List.of("Noodles", "Chicken", "Seafood", "Scallions", "Bok choy", "Bean Sprouts")
            )
        ));

        return ingredents;
     }

     private void onSelectCuisine(){
         // Get dishes for selected cuisine
         dishesList.getItems().clear();
         dishIngredentsArea.setText("");
         ingredents.forEach(t -> {
             if (t.getKey().equals(mealCategory.getValue())) {
                 dishesList.getItems().addAll(t.getValue().getKey());
             }
         });
     }

    @SuppressWarnings("unchecked")
    private void onSelectDish (){
        dishIngredentsArea.setText("");
            var selectedIngredents = new ArrayList<String>();
            dishesList.getItems().forEach(dish -> {
                dishesList.getSelectionModel().getSelectedItems().forEach(selectedDish -> {
                    ingredents.forEach(cuisine -> {
                        if (cuisine.getKey().equals(mealCategory.getValue())) {     // selected cuisine
                            if (cuisine.getValue().getKey().equals(selectedDish)) { // selected dish
                                cuisine.getValue().getValue().forEach(ingredent -> {
                                    selectedIngredents.add(ingredent);
                                });
                            }
                        }
                    });
                });
            });
            var distinctSelectedIngredents = selectedIngredents.stream().distinct().collect(Collectors.toList());
            distinctSelectedIngredents.forEach(t -> dishIngredentsArea.appendText(t + "\n"));
    }
    
    public static void main(String[] arg0) {
        launch(arg0);
    }
}
