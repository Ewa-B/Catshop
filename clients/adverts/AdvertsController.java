package clients.adverts;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdvertsController {

    AdvertsModel model;
    AdvertsView view;


    public AdvertsController(AdvertsModel model, AdvertsView view) {
        this.model = model;
        this.view = view;
    }

    public void play(){
        model.initMedia();
        model.mediaPlayer.play();
        view.getBtnPlay().setVisible(false);
        view.getBtnPause().setVisible(true);
    }

    public void pause(){
        view.getBtnPause().setVisible(false);
        view.getBtnPlay().setVisible(true);
        model.mediaPlayer.pause();
    }

}
