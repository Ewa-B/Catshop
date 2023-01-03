package clients.adverts;

import clients.customer.CustomerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import middle.MiddleFactory;

import java.util.Observable;
import java.util.Observer;

public class AdvertsView{

    private static final int H = 300;       // Height of window pixels
    private static final int W = 400;
    private final Label theAction;
    private Button btnPlay;
    private Button btnPause;

    AdvertsController cont = null;



    public AdvertsView(Stage stage, MiddleFactory mf, int x, int y){
        stage.setWidth(W);
        stage.setHeight(H);
        stage.setY(y);
        stage.setX(x);

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



        Pane pane = new Pane();
        pane.getChildren().add(theAction);
        pane.getChildren().add(btnPlay);
        pane.getChildren().add(btnPause);


        Scene scene = new Scene(pane);
        pane.getStylesheets().add("catShop.css");
        pane.setId("Adverts");
        stage.setScene(scene);


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

    public void setBtnPause(Button btnPause) {
        this.btnPause = btnPause;
    }

    //    @Override
//    public void update(Observable o, Object arg) {
//
//    }
}
