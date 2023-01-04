package clients.adverts;

import clients.customer.CustomerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import middle.MiddleFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class AdvertsView implements Observer {

    private static final int H = 500;       // Height of window pixels
    private static final int W = 420;
    private final Label theAction;
    private Button btnPlay;
    private Button btnPause;

    private ImageView imageView;

    private int counter;

    AdvertsController cont = null;

    AdvertsModel model = null;





    public AdvertsView(Stage stage, MiddleFactory mf, int x, int y){
        stage.setWidth(W);
        stage.setHeight(H);
        stage.setY(y);
        stage.setX(x);

        slideShow();

        theAction = new Label();
        theAction.setText("TODAY'S OFFERS");
        theAction.setTranslateX((W/2) - 80);
        theAction.setTranslateY(45);
        theAction.setId("adLabel");

        btnPlay = new Button("Play Music");
        btnPlay.setTranslateY(5);
        btnPlay.setTranslateX(5);
        btnPlay.setOnAction(event -> cont.play());
        //btnPlay.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> cont.play());

        btnPause = new Button("Pause music");
        btnPause.setVisible(false);
        btnPause.setTranslateY(5);
        btnPause.setTranslateX(100);
        btnPause.setOnAction(event -> cont.pause());
        btnPause.setId("MusicPause");
        btnPlay.setId("MusicPlay");

        imageView = new ImageView();
        imageView.setTranslateY(80);
        imageView.setTranslateX(10);
        imageView.setPreserveRatio(true);
        imageView.setId("AdImg");





        Pane pane = new Pane();
        pane.getChildren().add(theAction);
        pane.getChildren().add(btnPlay);
        pane.getChildren().add(btnPause);
        pane.getChildren().add(imageView);


        Scene scene = new Scene(pane);
        pane.getStylesheets().add("catShop.css");
        pane.setId("Adverts");
        stage.setScene(scene);


    }
    private void slideShow() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("ad1.jpg"));
        images.add(new Image("ad2.jpg"));
        images.add(new Image("ad3.jpg"));
        images.add(new Image("ad4.png"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            imageView.setImage(images.get(counter));
            counter++;
            if (counter == 3) {
                counter = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void setController( AdvertsController c )
    {
        cont = c;
    }
    public Button getBtnPlay() {
        return btnPlay;
    }

    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    public Label getTheAction() {
        return theAction;
    }

    public Button getBtnPause() {
        return btnPause;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setBtnPause(Button btnPause) {
        this.btnPause = btnPause;
    }


    @Override
    public void update(Observable o, Object arg) {
       //
        //Platform.runLater(() -> cont.slideshow());
    }


}
