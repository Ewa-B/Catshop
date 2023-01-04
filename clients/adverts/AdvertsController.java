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

    AdvertsModel model = null;
    AdvertsView view = null;
    private int counter;


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

//    public void slideshow(){
//        ArrayList<Image> images=new ArrayList<>();
//
//        images.add(new Image("ad1.jpg"));
//        images.add(new Image("ad2.jpg"));
//        images.add(new Image("ad3.jpg"));
//        images.add(new Image("ad4.png"));
//
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event ->{
//           view.getImageView().setImage(images.get(counter));
//           counter++;
//           if(counter == 3){
//               counter = 0;
//           }
//
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
    public void pause(){
        view.getBtnPause().setVisible(false);
        view.getBtnPlay().setVisible(true);
        model.mediaPlayer.pause();
    }

}
