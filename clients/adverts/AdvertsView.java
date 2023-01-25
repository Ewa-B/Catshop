package clients.adverts;

import clients.customer.CustomerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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


import javax.xml.soap.Node;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdvertsView implements Observer {

    private static final int H = 400;       // Height of window pixels
    private static final int W = 500;
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
        theAction.setText("TODAY'S OFFER: 10% OFF EVERYTHING");
        theAction.setTranslateX(40);
        theAction.setTranslateY(45);
        theAction.setId("adLabel");

        Label promoCode = new Label();
        promoCode.setText("Enter promo code 'CATSHOP' at checkout");
        promoCode.setTranslateY(80);
        promoCode.setTranslateX(45);
        promoCode.setId("adLabel");

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
        imageView.setTranslateY(120);
        imageView.setTranslateX(65);
        imageView.setPreserveRatio(true);
        imageView.setId("AdImg");

        Pane pane = new Pane();

        pane.getChildren().add(theAction);
        pane.getChildren().add(btnPlay);
        pane.getChildren().add(btnPause);
        pane.getChildren().add(imageView);
        pane.getChildren().add(promoCode);

        Scene scene = new Scene(pane);
        pane.getStylesheets().add("catShop.css");
        pane.setId("Adverts");
        stage.setScene(scene);
    }

    private void slideShow() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("/images/advert1.jpg"));
        images.add(new Image("/images/advert2.jpg"));
        images.add(new Image("/images/advert3.jpg"));
        images.add(new Image("/images/advert4.jpg"));
        images.add(new Image("/images/advert5.jpg"));
        images.add(new Image("/images/advert6.jpg"));
        images.add(new Image("/images/advert7.jpg"));
        images.add(new Image("/images/advert8.jpg"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            imageView.setImage(images.get(counter));
            counter++;
            if (counter == 8) {
                counter = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public AdvertsController getCont() {
        return cont;
    }

    public void setController(AdvertsController c )
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
