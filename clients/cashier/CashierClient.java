package clients.cashier;

import catalogue.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import middle.MiddleFactory;
import middle.Names;
import middle.RemoteMiddleFactory;


/**
 * The standalone Cashier Client.
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */


public class CashierClient extends Application
{
    public static RemoteMiddleFactory mrf;

   public static void main (String args[])
   {
     String stockURL = args.length < 1     // URL of stock RW
                     ? Names.STOCK_RW      //  default  location
                     : args[0];            //  supplied location
     String orderURL = args.length < 2     // URL of order
                     ? Names.ORDER         //  default  location
                     : args[1];            //  supplied location

     mrf = new RemoteMiddleFactory();
    mrf.setStockRWInfo( stockURL );
    mrf.setOrderInfo  ( orderURL );        //
    launch(args);                       // Create GUI
  }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cashier Client (MVC RMI)");
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        CashierModel      model = new CashierModel(mrf);
        CashierView       view  = new CashierView( primaryStage, mrf, 0, 0 );
        CashierController cont  = new CashierController( model, view );
        view.setController( cont );
            model.addObserver( view );       // Add observer to the model
    primaryStage.show();         // Display Screen
    model.askForUpdate();
    }

}
