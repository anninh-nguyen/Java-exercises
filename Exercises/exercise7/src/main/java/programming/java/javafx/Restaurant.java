package programming.java.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Restaurant extends Application {
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;

    private GridPane getMealPane()
    {
        Text tex1 = new Text("Meal Menu of the Day");
        tex1.setFont(Font.font ("Verdana", 20));
        tex1.setFill(Color.RED);
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

        // Create a GridPane
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10, 10, 10, 10)); // set insets
        gp.setStyle("-fx-background-color: #E6F9E9");
        gp.setAlignment(Pos.CENTER); gp.setVgap(5);
        gp.add(tex1, 0, 0);
        gp.add(cb1, 0, 1);
        gp.add(cb2, 0, 2);
        gp.add(cb3, 0, 3); // 2nd row
        gp.add(cb4, 0, 4);
        gp.add(cb5, 0, 5); // 3rd row

        return gp;
    }
    private int appleCount = 0;
    private int orangeCount = 0;
    private int mangoCount = 0;
    private GridPane getDessertPane() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10, 10, 10, 10)); // set insets
        gp.setStyle("-fx-background-color: #FFFFFF");

        ImageView oraImg = new ImageView("Orange3.jpg"); oraImg.setFitHeight(40); oraImg.setFitWidth(40);
        ImageView appImg = new ImageView("apple.jpg"); appImg.setFitHeight(45); appImg.setFitWidth(60);
        ImageView manImg = new ImageView("yellow-mango.jpeg"); manImg.setFitHeight(55); manImg.setFitWidth(75);
        ImageView resetImg = new ImageView("reset.png"); resetImg.setFitHeight(55); resetImg.setFitWidth(65);

        gp.add(oraImg, 0, 0);
        gp.add(appImg, 1, 0);
        gp.add(manImg, 2, 0);
        gp.add(resetImg, 3, 0);

        Text oraText = new Text("     0");
        Text appText = new Text("           0");
        Text manText = new Text("            0");

        gp.add(oraText,0,1);
        gp.add(appText,1,1);
        gp.add(manText,2,1);

        oraImg.setOnMouseClicked(e -> {
            orangeCount++; oraText.setText("     "+orangeCount);
        });
        appImg.setOnMouseClicked(e -> {
            appleCount++; appText.setText("           "+appleCount);
        });
        manImg.setOnMouseClicked(e -> {
            mangoCount++; manText.setText("            "+mangoCount);
        });
        resetImg.setOnMouseClicked(e -> {
            mangoCount=orangeCount=appleCount=0;
            oraText.setText("     "+orangeCount);
            appText.setText("           "+appleCount);
            manText.setText("            "+mangoCount);
        });
        return gp;
    }

    private double hothot;
    private VBox getHotPane()
    {
        Text tfi = new Text("Spice Level ( 0,0 )");
        tfi.setFont(Font.font ("Verdana", 20));
        tfi.setFill(Color.GREEN); this.hothot = 0;

        ScrollBar sb = new ScrollBar();
        sb.setOrientation(Orientation.HORIZONTAL);
        sb.setPrefSize(200,25);

        ImageView imgv = new ImageView("mild.jpg");
        imgv.setFitHeight(25); imgv.setFitWidth(25);

        sb.valueProperty().addListener(ov -> {
            hothot = sb.getValue();
            tfi.setText(String.format("Spice Level ( %.2f )", hothot));
            if (hothot >= 90) {
                tfi.setFill(Color.BLACK);
                imgv.setImage(new Image("skull.png"));
                imgv.setFitHeight(25); imgv.setFitWidth(25);
            }
            else if (hothot >= 70) {
                tfi.setFill(Color.RED);
                imgv.setImage(new Image("hot.jpg"));
                imgv.setFitHeight(25); imgv.setFitWidth(25);
            }
            else if (hothot >= 35){
                tfi.setFill(Color.BLUE);
                imgv.setImage(new Image("medium.jpg"));
                imgv.setFitHeight(25); imgv.setFitWidth(25);
            }
            else{
                tfi.setFill(Color.GREEN);
                imgv.setImage(new Image("mild.jpg"));
                imgv.setFitHeight(25); imgv.setFitWidth(25);
            }
        });
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.getChildren().addAll(tfi,imgv);

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(hb,sb);
        vb.setPadding(new Insets(10,10,10,10));
        return vb;
    }

    private RadioButton rb;
    private RadioButton rb2;
    private RadioButton rb3;
    private GridPane getRadioPane()
    {// RadioButtons in a ToggleGroup
        ToggleGroup tg = new ToggleGroup();
        rb = new RadioButton("Table 1");
        rb.setSelected(true);
        rb2 = new RadioButton("Table 2");
        rb3 = new RadioButton("Table 3");
        rb.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);

        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setPadding(new Insets(30, 30, 30, 30)); // set insets
        gp.setStyle("-fx-background-color: #D3DAFB");

        gp.add(rb, 0, 0);
        gp.add(rb2, 1, 0);
        gp.add(rb3, 2, 0);
        return gp;
    }

    private void placeOrder(){
        double sum = 0;
        String mealText = "";
        mealText += "ORDER SUMMARY ("+tableCbo.getValue()+")\n\n";
        /*
        if (rb.isSelected()) {mealText += "ORDER SUMMARY (TABLE 1)\n\n";}
        if (rb2.isSelected()) {mealText += "ORDER SUMMARY (TABLE 2)\n\n";}
        if (rb3.isSelected()) {mealText += "ORDER SUMMARY (TABLE 3)\n\n";}
         */
        mealText += String.format("MAIN COURSE: (Spices: %.2f) \n",this.hothot);
        if (cb1.isSelected()) {mealText += " - One cup of soup (3€)\n";sum+=3;}
        if (cb2.isSelected()) {mealText += " - Falafel (6€)\n";sum+=6;}
        if (cb3.isSelected()) {mealText += " - Karelia Pie (2€)\n";sum+=2;}
        if (cb4.isSelected()) {mealText += " - Surprice Meal (1€)\n";sum+=1;}
        if (cb5.isSelected()) {mealText += " - Sauce (1€)\n";sum+=1;}
        mealText += "DESSERTS:\n";
        if (orangeCount > 0) {mealText += " - Oranges ("+orangeCount+" pcs [0.5€ each]), ("+(orangeCount*0.5)+"€)\n"; sum+=(orangeCount*0.5);}
        if (appleCount > 0) {mealText += " - Apples ("+appleCount+" pcs [0.4€ each]), ("+(appleCount*0.4)+"€)\n"; sum+=(appleCount*0.4);}
        if (mangoCount > 0) {mealText += " - Mangos ("+mangoCount+" pcs [0.7€ each]), ("+(mangoCount*0.7)+"€)\n"; sum+=(mangoCount*0.7);}
        mealText += "\nSUM TOTAL:"+sum+"€";
        summaryText.setText(mealText);
    }

    private TextArea summaryText;
    private ComboBox<String> tableCbo;
    public void start(Stage primaryStage) {
        // Get GridPanes for meals, desserts, and radiobuttons
        GridPane mealPane = getMealPane();
        GridPane dessertPane = getDessertPane();
        //GridPane radioPane = getRadioPane();

        tableCbo = new ComboBox<>();
        tableCbo.getItems().addAll("Table 1", "Table 2", "Table 3", "Table 4");
        tableCbo.setStyle("−fx−color: #EB0D1B");
        tableCbo.setValue("Table 1");

        VBox hotPane = getHotPane();

        // Create a TextArea for the order summary
        summaryText = new TextArea("");
        summaryText.setEditable(false); // set as non-editable
        summaryText.setPrefRowCount(10);
        summaryText.setPrefColumnCount(5);

        // Create a title text for desserts-section
        Text desserttext = new Text("DEssERTs");
        desserttext.setFont(Font.font ("Verdana", 20));
        desserttext.setFill(Color.DEEPSKYBLUE);

        // Create a PLACE ORDER-button
        Button orderButton = new Button("PLACE ORDER!");
        orderButton.setStyle("-fx-background-color: #D3DAFB");

        /*
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(30, 30, 30, 30)); // set insets
        gp.setStyle("-fx-background-color: #E6F9E9");
        gp.setAlignment(Pos.CENTER); gp.setVgap(5);
        gp.add(mealPane, 0, 0);
        gp.add(hotPane , 1, 0);
        gp.add(desserttext, 0, 1);
        gp.add(dessertPane, 0, 2);
        gp.add(radioPane, 1, 1);
        gp.add(orderButton, 1, 2);
        gp.add(summaryText, 1, 3);
        //vb.getChildren().addAll(mealPane,getHotPane(),desserttext,dessertPane,radioPane,orderButton,summaryText);
*/

        // Create a VBox
        VBox vb = new VBox();
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        // Add 3 GridPanes, title text, order button, and textarea to vertical box
        //vb.getChildren().addAll(mealPane,getHotPane(),desserttext,dessertPane,radioPane,orderButton,summaryText);
        vb.getChildren().addAll(mealPane,getHotPane(),desserttext,dessertPane,tableCbo,orderButton,summaryText);

        vb.setPadding(new Insets(30,30,30,30)); // set insets





        // Create event for placing order
        orderButton.setOnAction(e->{
            this.placeOrder();
        });

        /*
        vb.setOnMouseClicked(e -> {
            summaryText.setText("CLICCKED!");
        });*/


        // Add the GridPane on the scene, set the scene on the stage, and show it
        Scene scene = new Scene(vb, 400, 800);// Create a Scene, including VBox
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene); // Set the Scene on the Stage
        primaryStage.setTitle("Street Restaurant");
        primaryStage.show(); // Show the Stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
