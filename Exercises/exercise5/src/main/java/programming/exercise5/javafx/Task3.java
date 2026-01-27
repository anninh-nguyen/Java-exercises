package programming.exercise5.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Task3 extends Application {

    @Override
    public void start(Stage primarStage) throws Exception {

        Artwork[] artList = new Artwork[3];
        artList[0] = new Artwork("Pikku Papun Orkesteri","Ötökkä höpöttää","Lapset");
        artList[1] = new Artwork("T.T. Purontaka","Luotan Huomiseen","Album");
        artList[2] = new Artwork("Zac Brown Bank","Chicken Fried","Song");

        Scene primaryScene = new Scene(genGridPane(artList[2]));
        primaryScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primarStage.setScene(primaryScene);
        primarStage.setX(50);
        primarStage.setY(50);
        primarStage.setTitle("Task #3 (a)");
        primarStage.show();

        HBox hbox = new HBox();
        hbox.setSpacing(30);
        for (Artwork artwork : artList) {
            hbox.getChildren().addAll(genGridPane(artwork));
        }
        Scene secondScene = new Scene(hbox);
        secondScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setX(350);
        secondStage.setY(350);
        secondStage.setTitle("Task #3 (b)");
        secondStage.show();
    }

    private GridPane genGridPane (Artwork artwork) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        gridPane.add(new Text("Art work name"), 0, 0);
        gridPane.add(new Text("Author"), 0, 1);
        gridPane.add(new Text("Type"), 0, 2);
        gridPane.add(new TextField(artwork.getname()), 1, 0);
        gridPane.add(new TextField(artwork.getAuthor()), 1, 1);
        gridPane.add(new TextField(artwork.getGenre()), 1, 2);
        gridPane.add(new Button("Play"), 1, 3);

        return gridPane;
    }

    public static void main(String[] args) {
        launch();
    }
}
