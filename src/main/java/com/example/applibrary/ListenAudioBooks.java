package com.example.applibrary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ListenAudioBooks implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> BoxSpeed;

    @FXML
    private Button buttonNext;

    @FXML
    private Button buttonPause;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonPrevious;

    @FXML
    private ImageView icon;

    @FXML
    private Label songLabel;

    @FXML
    private ProgressBar songProgressBar;

    @FXML
    private Slider volumeSlider;

    @FXML
    private ImageView next;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView play;

    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private ImageView previous;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;
    private int songNumber;
    private int[] speeds = {25,50,75,100,125,150,175,200};
    private Timer timer;
    private TimerTask task;
    private boolean running;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/java/img/logo121.png");
        javafx.scene.image.Image brandingImage = new Image(brandingFile.toURI().toString());
        icon.setImage(brandingImage);

        File nextFile = new File("src/main/java/img/next_100px.png");
        javafx.scene.image.Image nextImage = new Image(nextFile.toURI().toString());
        next.setImage(nextImage);

        File playFile = new File("src/main/java/img/play_96px.png");
        javafx.scene.image.Image playImage = new Image(playFile.toURI().toString());
        play.setImage(playImage);

        File previousFile = new File("src/main/java/img/previous_100px.png");
        javafx.scene.image.Image previousImage = new Image(previousFile.toURI().toString());
        previous.setImage(previousImage);

        File pauseFile = new File("src/main/java/img/pause_60px.png");
        javafx.scene.image.Image pauseImage = new Image(pauseFile.toURI().toString());
        pause.setImage(pauseImage);

        songs = new ArrayList<File>();
        directory = new File("C:\\Users\\User\\Desktop\\AppLibrary\\src\\main\\java\\music");
        files = directory.listFiles();
        if(files != null){

            for(File file : files){
                songs.add(file);
            }

        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songLabel.setText(songs.get(songNumber).getName());

        for(int i = 0;i<speeds.length;i++){

            BoxSpeed.getItems().add(Integer.toString(speeds[i])+"%");

        }

        BoxSpeed.setOnAction(this::changeSpeed);

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });

    }

    public void playMedia(){
        beginTimer();
        changeSpeed(null);
        mediaPlayer.play();
    }

    public void pauseMedia(){
        cancelTimer();
        mediaPlayer.pause();
    }

    public void previousMedia(){
        if(songNumber > 0){
            songNumber--;
            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
        else{
            songNumber = songs.size()-1;
            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
    }

    public void nextMedia(){
        if(songNumber < songs.size() - 1){
            songNumber++;
            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
        else{
            songNumber = 0;
            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
    }

    public void changeSpeed(ActionEvent event){
        if(BoxSpeed.getValue() == null){
            mediaPlayer.setRate(1);
        }else {
//        mediaPlayer.setRate(Integer.parseInt(BoxSpeed.getValue()) * 0.01);
            mediaPlayer.setRate(Integer.parseInt(BoxSpeed.getValue().substring(0, BoxSpeed.getValue().length() - 1)) * 0.01);
        }
    }

    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);
                if(current/end == 1){
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);

    }

    public void cancelTimer(){
        running = false;
        timer.cancel();
    }

    @FXML
    private Button btnBack;
    public void backButtonOnAction(ActionEvent event) throws IOException {
        pauseMedia();
        Parent root = FXMLLoader.load(getClass().getResource("readerBook.fxml"));
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(root,1000,500));
    }


}
