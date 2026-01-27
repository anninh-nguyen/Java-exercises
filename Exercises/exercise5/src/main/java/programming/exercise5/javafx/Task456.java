package programming.exercise5.javafx;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task456 extends Application {

    @Override
    public void start(Stage primarStage) throws Exception {

        ArrayList<Artwork> artList = new ArrayList<Artwork>();
        artList.add(new Book("Pikku Papun Orkesteri","Ötökkä höpöttää","Lapset", "t21.jpg", 342));
        artList.add(new Song("T.T. Purontaka","Luotan Huomiseen","Romantic", "t22.jpg", 249, 300000));
        artList.add(new Painting("Leonardo Da Vinci","Mona Lisa","Classic", "t23.jpg", 350.4));
        artList.add(new Song("Zac Brown Bank","Chicken Fried","R&B", "t24.jpg", 65, 20000));
        artList.add(new Painting("Piet Mondrian","Composition en rouge, jaune, bleu et noir","Abstract art", "t25.jpg", 50979000));

        HBox hbox = new HBox();
        hbox.setSpacing(20);
        Scene primaryScene = new Scene(hbox);

        for (Artwork artwork : artList) {
            hbox.getChildren().add(genGridPane(artwork));
        }
        primaryScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primarStage.setScene(primaryScene);
        primarStage.setX(50);
        primarStage.setY(50);
        primarStage.setTitle("Task #4 (a)");
        primarStage.show();

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        for (Artwork artwork : artList) {
            vbox.getChildren().add(genGridPane(artwork));
        }
        Scene secondScene = new Scene(vbox);
        secondScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setX(250);
        secondStage.setY(250);
        secondStage.setTitle("Task #4 (b)");
        secondStage.show();


        BorderPane borderPane = new BorderPane();
        borderPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        borderPane.setTop(genGridPane(artList.get(0)));
        borderPane.setLeft(genGridPane(artList.get(1)));
        borderPane.setCenter(genGridPane(artList.get(2)));
        borderPane.setRight(genGridPane(artList.get(3)));
        borderPane.setBottom(genGridPane(artList.get(4)));

        Scene thirdScene = new Scene(borderPane);
        thirdScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Stage thirdStage = new Stage();
        thirdStage.setScene(thirdScene);
        thirdStage.setX(450);
        thirdStage.setY(450);
        thirdStage.setTitle("Task #5");
        thirdStage.show();


        VBox vbox6 = new VBox();
        for (Artwork artwork : artList) {
            HBox hbox6 = new HBox();
            hbox6.setSpacing(10);
            hbox6.getChildren().add(genGridPane(artwork));
            hbox6.getChildren().add(new ImageView(new Image(artwork.getThumbnail())));
            vbox6.getChildren().add(hbox6);
        }

        ScrollPane scrollPane = new ScrollPane(vbox6);
        scrollPane.setPannable(true);

        Scene fourthScene = new Scene(scrollPane);
        fourthScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Stage fourthStage = new Stage();
        fourthStage.setScene(fourthScene);
        fourthStage.setX(250);
        fourthStage.setY(250);
        fourthStage.setTitle("Task #6");
        fourthStage.show();

    }

    private GridPane genGridPane (Artwork artwork) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        gridPane.add(new Text("Art work name"), 0, 0);
        gridPane.add(new Text("Author"), 0, 1);
        gridPane.add(new Text("Genre"), 0, 2);
        gridPane.add(new TextField(artwork.getname()), 1, 0);
        gridPane.add(new TextField(artwork.getAuthor()), 1, 1);
        gridPane.add(new TextField(artwork.getGenre()), 1, 2);
        
        if (artwork instanceof Song) {
            gridPane.add(new Text("Length (s)"), 0, 3);
            gridPane.add(new Text("Played time"), 0, 4);
            gridPane.add(new TextField(Integer.toString(((Song)artwork).getSeconds())), 1, 3);
            gridPane.add(new TextField(Integer.toString(((Song)artwork).getTimeplayed())), 1, 4);

            gridPane.add(new Button("Play"), 1, 5);
        }

        if (artwork instanceof Book) {
            gridPane.add(new Text("Number of Page"), 0, 3);
            gridPane.add(new TextField(Integer.toString(((Book)artwork).getNoPages())), 1, 3);

            gridPane.add(new Button("Read"), 1, 4);
        }

        if (artwork instanceof Painting) {
            gridPane.add(new Text("Price ($)"), 0, 3);
            gridPane.add(new TextField(Double.toString(((Painting)artwork).getPrice())), 1, 3);

            gridPane.add(new Button("View"), 1, 4);
        }

        return gridPane;
    }

    public static void main(String[] args) {
        launch();
    }

}
