package clients.adverts;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import middle.MiddleFactory;

import java.nio.file.Paths;
import java.util.ArrayList;

public class AdvertsModel {

    AdvertsView view;
    AdvertsController controller;
    private Image image = null;
    Media media;
    MediaPlayer mediaPlayer;
    private int counter;

//    public AdvertsModel(MiddleFactory mf) {
//
//    }
    public AdvertsModel(AdvertsView view){
        this.view = view;
    }

    public void initMedia(){
        String music = "Long.mp3";
        media = new Media(Paths.get(music).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/MediaPlayer.html
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }


}

