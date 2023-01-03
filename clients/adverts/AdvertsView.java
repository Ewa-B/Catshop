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

    AdvertsController cont = null;

    public Button getBtnPlay() {
        return btnPlay;
    }

    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    public AdvertsView(Stage stage, MiddleFactory mf, int x, int y){
        stage.setWidth(W);
        stage.setHeight(H);
        stage.setY(y);
        stage.setX(x);

        theAction = new Label();
        theAction.setTranslateX(100);
        theAction.setTranslateY(30);
        //theAction.setPrefSize(270, 20);
        theAction.setText("TODAY'S OFFERS");

        btnPlay = new Button("Play Music");
        btnPlay.setOnAction(event -> cont.play());
       //btnPlay.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> cont.play());



        Pane pane = new Pane();
        pane.getChildren().add(theAction);
        pane.getChildren().add(btnPlay);
        Scene scene = new Scene(pane);
        pane.getStylesheets().add("catShop.css");
        pane.setId("Adverts");
        stage.setScene(scene);


    }
    public void setController( AdvertsController c )
    {
        cont = c;
    }


//    @Override
//    public void update(Observable o, Object arg) {
//
//    }
}
