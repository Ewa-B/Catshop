package clients.adverts;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import middle.MiddleFactory;

import java.util.Observable;
import java.util.Observer;

public class AdvertsView implements Observer {

    private static final int H = 300;       // Height of window pixels
    private static final int W = 400;
    private final Label theAction  = new Label();
    private Button btnPlay = new Button("Play");

    AdvertsController cont = null;

    public AdvertsView(Stage stage, MiddleFactory mf, int x, int y){
        stage.setWidth(W);
        stage.setHeight(H);
        stage.setY(y);
        stage.setX(x);

        theAction.setPrefSize(270, 20);
        theAction.setText("TODAY'S OFFERS");

        Pane pane = new Pane();
        pane.getChildren().add(theAction);

    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
