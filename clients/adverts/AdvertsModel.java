package clients.adverts;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import middle.MiddleFactory;

import java.nio.file.Paths;

public class AdvertsModel {

    AdvertsView view;
    AdvertsController controller;
    private Image image = null;
    Media media;
    MediaPlayer mediaPlayer;

    public AdvertsModel(MiddleFactory mf) {

    }

    public void initMedia(){
        String music = "Long.mp3";
        media = new Media(Paths.get(music).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
}

