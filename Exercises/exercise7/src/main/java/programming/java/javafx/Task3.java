package programming.java.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/*
 * Part of media player's code related in this program is suggested by ChatGPT-4o
 */
public class Task3 extends Application {
    private Label volumeLabel, balanceLabel, seekLabel;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Slider seekSlider;

    @Override
    public void start(Stage primaryStage) {

        Slider volumeSlider = createSlider("Volume");
        Slider balanceSlider = createSlider("Balance");
        seekSlider = createSlider("Seeker");
        seekSlider.setPrefWidth(760);
        
        volumeLabel = new Label("Volume: 50%");
        balanceLabel = new Label("Balance: 0%");
        seekLabel = new Label("00:00 / 00:00");

        mediaView = new MediaView();
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);
        mediaView.setFitWidth(1180);
        mediaView.setFitHeight(670);

        Button openFileButton = new Button("Open media file");
        openFileButton.setOnAction(e -> openMedia(primaryStage, volumeSlider, balanceSlider));
        // Button openStreamButton = new Button("Open stream media");
        // openStreamButton.setOnAction(e -> openMedia("https://www.youtube.com/embed/yR6tVxgDWLk",
        //                                             volumeSlider, balanceSlider));
        
        volumeSlider.valueProperty().addListener(e -> updateAudioSettings(volumeSlider, balanceSlider));
        balanceSlider.valueProperty().addListener(e -> updateAudioSettings(volumeSlider, balanceSlider));
        seekSlider.valueProperty().addListener(e -> seekMedia());

        var mediaControls = new GridPane();
        mediaControls.setHgap(20);
        mediaControls.setVgap(10);
        mediaControls.add(new Label ("MP3/MP4 file"), 0, 0);
        mediaControls.add(openFileButton, 0, 1);        mediaControls.add(seekLabel, 1, 0);
        mediaControls.add(seekSlider, 1, 1);
        mediaControls.add(volumeLabel, 2, 0);
        mediaControls.add(volumeSlider, 2, 1);
        mediaControls.add(balanceLabel, 3, 0);
        mediaControls.add(balanceSlider, 3, 1);

        VBox main = new VBox(10, mediaView, mediaControls);
        main.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(main, 1200, 800));
        primaryStage.setTitle("Media Player");
        primaryStage.show();
    }

    private Slider createSlider(String label) {
        Slider slider = new Slider(0, 100, 50);
        //slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        return slider;
    }

    private void openMedia(Stage parentStage, Slider volumeSlider, Slider balanceSlider) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Media Files", "*.mp3", "*.mp4"));
        File file = fileChooser.showOpenDialog(parentStage);
        if (file != null) {
            parentStage.setTitle("Playing " + file.toURI().toString());
            Media media = new Media(file.toURI().toString());
            playMedia(media, volumeSlider, balanceSlider);
        }
    }

    // private void openMedia(String mediaUrl, Slider volumeSlider, Slider balanceSlider){
    //     Media media = new Media(mediaUrl);
    //     playMedia(media, volumeSlider, balanceSlider);
    // }

    private void playMedia(Media media, Slider volumeSlider, Slider balanceSlider) {
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.stop();
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setVolume(volumeSlider.getValue() / 100);
        mediaPlayer.setBalance((balanceSlider.getValue() - 50) / 50.0);
        mediaPlayer.currentTimeProperty().addListener(e -> updateTimeSlider());
        mediaPlayer.setOnReady(() -> {
            seekSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
        });
        mediaPlayer.play();
    }

    private void updateAudioSettings(Slider volume, Slider balance) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume.getValue() / 100);
            mediaPlayer.setBalance((balance.getValue() - 50) / 50.0);
        }
        volumeLabel.setText("Volume: " + (int) volume.getValue() + "%");
        balanceLabel.setText("Balance: " + ((int) balance.getValue() - 50) + "%");
    }

    private void updateTimeSlider() {
        if (mediaPlayer != null) {
            seekSlider.setValue(mediaPlayer.getCurrentTime().toSeconds());
            seekLabel.setText(String.format("%02d:%02d / %02d:%02d",
                    (int) mediaPlayer.getCurrentTime().toMinutes(), (int) mediaPlayer.getCurrentTime().toSeconds() % 60,
                    (int) mediaPlayer.getTotalDuration().toMinutes(), (int) mediaPlayer.getTotalDuration().toSeconds() % 60));
        }
    }

    private void seekMedia() {
        if (mediaPlayer != null && seekSlider.isValueChanging()) {
            mediaPlayer.seek(javafx.util.Duration.seconds(seekSlider.getValue()));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
