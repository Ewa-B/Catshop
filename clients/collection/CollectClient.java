//package clients.collection;
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
// * The standalone Collection Client.
// * @author  Mike Smith University of Brighton
// * @version 2.0
// */
//
//
//public class CollectClient extends Application
//{
//    public static RemoteMiddleFactory mrf;
//    public static void main (String args[])
//   {
//     String stockURL = args.length < 1     // URL of stock RW
//                     ? Names.STOCK_RW      //  default  location
//                     : args[0];            //  supplied location
//     String orderURL = args.length < 2     // URL of order
//                     ? Names.ORDER         //  default  location
//                     : args[1];            //  supplied location
//
//    mrf = new RemoteMiddleFactory();
//    mrf.setStockRWInfo( stockURL );
//    mrf.setOrderInfo  ( orderURL );        //
//    launch(args);                      // Create GUI
//  }
//
////  private static void displayGUI(MiddleFactory mf)
////  {
////    JFrame  window = new JFrame();
////
////    window.setTitle( "Collection Client (MVC RMI)");
////    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
////
////	CollectModel      model = new CollectModel(mf);
////	CollectView       view  = new CollectView( window, mf, 0, 0 );
////	CollectController cont  = new CollectController( model, view );
////	view.setController( cont );
////
////	model.addObserver( view );       // Add observer to the model
////	window.setVisible(true);         // Display Screen
////  }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle( "Collection Client (MVC RMI)");
//        primaryStage.setOnCloseRequest(event -> {
//            Platform.exit();
//            System.exit(0);
//        });
//
//	CollectModel      model = new CollectModel(mrf);
//	CollectView       view  = new CollectView( primaryStage, mrf, 0, 0 );
//	CollectController cont  = new CollectController( model, view );
//	view.setController( cont );
//
//	model.addObserver( view );       // Add observer to the model
//    primaryStage.show();
//    }
//}
