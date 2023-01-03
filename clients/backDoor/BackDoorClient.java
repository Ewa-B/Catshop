//package clients.backDoor;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.EventHandler;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import middle.MiddleFactory;
//import middle.Names;
//import middle.RemoteMiddleFactory;
//
////import javax.swing.*;
//
///**
// * The standalone BackDoor Client
// * @author  Mike Smith University of Brighton
// * @version 2.0
// */
//
//
//public class BackDoorClient extends Application
//{
//    //public static RemoteMiddleFactory mrf;
//    public static void main (String args[])
//   {
//     String stockURL = args.length < 1     // URL of stock RW
//                     ? Names.STOCK_RW      //  default  location
//                     : args[0];            //  supplied location
//     String orderURL = args.length < 2     // URL of order
//                     ? Names.ORDER         //  default  location
//                     : args[1];            //  supplied location
//
//       RemoteMiddleFactory mrf = new RemoteMiddleFactory();
//    mrf.setStockRWInfo( stockURL );
//    mrf.setOrderInfo  ( orderURL );
//    launch(args);
////  }
////
////  private static void displayGUI(MiddleFactory mf)
////  {
////    Frame  window = new Frame();
////
////    window.setTitle( "BackDoor Client (MVC RMI)");
////    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
////
////    BackDoorModel      model = new BackDoorModel(mf);
////    BackDoorView       view  = new BackDoorView( window, mf, 0, 0 );
////    BackDoorController cont  = new BackDoorController( model, view );
////    view.setController( cont );
////
////    model.addObserver( view );       // Add observer to the model
////    window.setVisible(true);         // Display Screen
// }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("BackDoor Client (MVC RMI)");
//        primaryStage.setOnCloseRequest(event -> {
//            Platform.exit();
//            System.exit(0);
//        });
//    }
//}
