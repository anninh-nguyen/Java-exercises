package programming.java.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task6 extends Application {

    private TextArea orderSummary;

    public void start(Stage primaryStage) {
        ScrollPane mealPane = getMealPane();
        GridPane dessertPane = getDessertPane();
        GridPane radioPane = getRadioPane();

        orderSummary = new TextArea("");
        orderSummary.setEditable(false); 
        orderSummary.setPrefRowCount(18);
        orderSummary.setPrefColumnCount(50);

        Text desserttext = new Text("DEssERTs");
        desserttext.setFont(Font.font ("Verdana", 20));
        desserttext.setFill(Color.DEEPSKYBLUE);

        // Create a PLACE ORDER-button
        Button orderButton = new Button("PLACE ORDER!");
        orderButton.setStyle("-fx-background-color: #D3DAFB");

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(mealPane,desserttext,dessertPane,radioPane,orderButton);
        vb.setPadding(new Insets(10));

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vb, orderSummary);

        orderButton.setOnAction(e->{
            this.placeOrder();
        });

        Scene scene = new Scene(hBox, 800, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Street Restaurant");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void placeOrder(){
        double sum = 0;
        String mealText = "";
        if (rb1.isSelected()) {mealText += "ORDER SUMMARY (TABLE 1)\n\n";}
        if (rb2.isSelected()) {mealText += "ORDER SUMMARY (TABLE 2)\n\n";}
        if (rb3.isSelected()) {mealText += "ORDER SUMMARY (TABLE 3)\n\n";}
        if (rb4.isSelected()) {mealText += "ORDER SUMMARY (TABLE 4)\n\n";}
        mealText += "MAIN COURSE:\n";
        if (cb1.isSelected()) {mealText += " - One cup of soup (1€)\n";sum+=1;}
        if (cb2.isSelected()) {mealText += " - Falafel (6€)\n";sum+=6;}
        if (cb3.isSelected()) {mealText += " - Karelia Pie (2€)\n";sum+=2;}
        if (cb4.isSelected()) {mealText += " - Surprice Meal (1€)\n";sum+=1;}
        if (cb5.isSelected()) {mealText += " - Sauce (1€)\n";sum+=1;}
        if (cb6.isSelected()) {mealText += " - Ratatouille (2€)\n";sum+=2;}
        mealText += "DESSERTS:\n";
        if (orangeCount > 0) {mealText += " - Oranges ("+orangeCount+" pcs [0.5€ each]), ("+(orangeCount*0.5)+"€)\n"; sum+=(orangeCount*0.5);}
        if (appleCount > 0) {mealText += " - Apples ("+appleCount+" pcs [0.4€ each]), ("+(appleCount*0.4)+"€)\n"; sum+=(appleCount*0.4);}
        if (mangoCount > 0) {mealText += " - Mangos ("+mangoCount+" pcs [0.7€ each]), ("+(mangoCount*0.7)+"€)\n"; sum+=(mangoCount*0.7);}
        if (mixCount > 0) {mealText += " - Mix ("+mixCount+" pcs [0.6€ each]), ("+(mixCount*0.6)+"€)\n"; sum+=(mixCount*0.6);}
        mealText += "\nSUM TOTAL:"+sum+"€";
        orderSummary.setText(mealText);
    }

    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;

    private ScrollPane getMealPane()
    {
        Text menuTitle = new Text("Meal Menu of the Day");
        menuTitle.setFont(Font.font ("Verdana", 20));
        menuTitle.setFill(Color.RED);

        cb1 = new CheckBox("Soup (3€)");
        cb1.setTextFill(Color.BLUEVIOLET);
        cb2 = new CheckBox("Falafel (6€)");
        cb2.setTextFill(Color.DARKBLUE);
        cb3 = new CheckBox("Karelia Pie (2€)");
        cb3.setTextFill(Color.DEEPPINK);
        cb4 = new CheckBox("Surprise Meal (1€)");
        cb4.setTextFill(Color.FUCHSIA);
        cb5 = new CheckBox("Sauce (1€)");
        cb5.setTextFill(Color.TURQUOISE);
        cb6 = new CheckBox("Ratatouille (2€)");
        cb6.setTextFill(Color.DODGERBLUE);

        // Create a GridPane
        GridPane gridMenu = new GridPane();
        gridMenu.setPadding(new Insets(10)); // set insets
        gridMenu.setHgap(10);
        gridMenu.setVgap(10);
        gridMenu.setStyle("-fx-background-color: #E6F9E9");
        gridMenu.setAlignment(Pos.CENTER); gridMenu.setVgap(5);
        gridMenu.add(menuTitle, 0, 0); GridPane.setColumnSpan(menuTitle, 2);

        gridMenu.add(new ImageView(new Image("Soup.jpeg", 150, 100, true, true)), 0, 1);
        gridMenu.add(cb1, 0, 2);
        gridMenu.add(new ImageView(new Image("Karelia_Pie.jpg", 150, 100, true, true)), 1, 1);
        gridMenu.add(cb2, 1, 2);
        gridMenu.add(new ImageView(new Image("Surprise_Meal.jpg", 150, 100, true, true)), 2, 1);
        gridMenu.add(cb3, 2, 2);

        gridMenu.add(new ImageView(new Image("Falafel.jpg", 150, 100, true, true)), 0, 3);
        gridMenu.add(cb4, 0, 4);
        gridMenu.add(new ImageView(new Image("Sauce.jpeg", 150, 100, true, true)), 1, 3);
        gridMenu.add(cb5, 1, 4);
        gridMenu.add(new ImageView(new Image("Ratatouille.jpg", 150, 100, true, true)), 2, 3);
        gridMenu.add(cb6, 2, 4);
        ScrollPane menuScroller = new ScrollPane(gridMenu);
        menuScroller.setPannable(true);

        return menuScroller;
    }

    private int appleCount = 0;
    private int orangeCount = 0;
    private int mangoCount = 0;
    private int mixCount = 0;

    private GridPane getDessertPane() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10)); // set insets
        gp.setStyle("-fx-background-color: #FFFFFF");

        ImageView oraImg = new ImageView(new Image("Orange3.jpg", 70, 70, true, true));
        ImageView appImg = new ImageView(new Image("apple.jpg", 100, 100, true, true));
        ImageView manImg = new ImageView(new Image("yellow-mango.jpeg", 100, 100, true, true));
        ImageView mixImg = new ImageView(new Image("mix.png", 100, 100, true, true));
        ImageView resetImg = new ImageView(new Image("reset.png", 70, 70, true, true));

        gp.add(oraImg, 0, 0);
        gp.add(appImg, 1, 0);
        gp.add(manImg, 2, 0);
        gp.add(mixImg, 3, 0);
        gp.add(resetImg, 4, 0);

        Text oraText = new Text("     0");
        Text appText = new Text("           0");
        Text manText = new Text("            0");
        Text mixText = new Text("          0");
        gp.add(oraText,0,1);
        gp.add(appText,1,1);
        gp.add(manText,2,1);
        gp.add(mixText,3,1);

        oraImg.setOnMouseClicked(e -> {
            orangeCount++; oraText.setText("     "+orangeCount);
        });
        appImg.setOnMouseClicked(e -> {
            appleCount++; appText.setText("           "+appleCount);
        });
        manImg.setOnMouseClicked(e -> {
            mangoCount++; manText.setText("            "+mangoCount);
        });
        mixImg.setOnMouseClicked(e -> {
            mixCount++; mixText.setText("            "+mixCount);
        });
        resetImg.setOnMouseClicked(e -> {
            mangoCount=orangeCount=appleCount=mixCount=0;
            oraText.setText("     "+orangeCount);
            appText.setText("           "+appleCount);
            manText.setText("            "+mangoCount);
            mixText.setText("            "+mixCount);
        });
        return gp;
    }

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

    private GridPane getRadioPane() {// RadioButtons in a ToggleGroup
        ToggleGroup tg = new ToggleGroup();
        rb1 = new RadioButton("Table 1");
        rb2 = new RadioButton("Table 2");
        rb3 = new RadioButton("Table 3");
        rb4 = new RadioButton("Table 4");
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);

        rb1.setSelected(true);

        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setPadding(new Insets(10)); // set insets
        gp.setStyle("-fx-background-color: #D3DAFB");

        gp.add(rb1, 0, 0);
        gp.add(rb2, 1, 0);
        gp.add(rb3, 2, 0);
        gp.add(rb4, 3, 0);
        return gp;
    }
}