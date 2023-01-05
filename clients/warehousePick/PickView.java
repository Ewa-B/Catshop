package clients.warehousePick;

import catalogue.Basket;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import middle.MiddleFactory;
import middle.OrderProcessing;

//import javax.swing.*;
//import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class PickView implements Observer
{
  private static final String PICKED = "Picked";

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final Label theAction  = new Label();
  private final TextArea theOutput  = new TextArea();
  private final ScrollPane theSP      = new ScrollPane();
  private final Button theBtPicked= new Button( PICKED );
 
  private OrderProcessing theOrder     = null;
  
  private PickController cont= null;

  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  public PickView(Stage stage, MiddleFactory mf, int x, int y )
  {
    try                                           // 
    {      
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    stage.setWidth(W);
    stage.setHeight(H);
    stage.setY(y);
    stage.setX(x);
//    Container cp         = rpc.getContentPane();    // Content Pane
//    Container rootWindow = (Container) rpc;         // Root Window
//    cp.setLayout(null);                             // No layout manager
//    rootWindow.setSize( W, H );                     // Size of Window
//    rootWindow.setLocation( x, y );
    
 //   Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

   // theBtPicked.setPrefSize(80, 40);   // Check Button
    theBtPicked.setOnAction(e -> cont.doPick() );// Call back code
//    theBtPicked.setId("btnPick");

    theAction.setPrefSize(250, 20);      // Message area
    theAction.setText( "" );                        // Blank

    theSP.setPrefSize(250, 205);          // Scrolling pane
    theOutput.setText( "" );  //  Blank
    theOutput.prefHeight(200);
    theSP.setContent(theOutput);
//    theOutput.setFont( f );                         //  Uses font
//    cp.add( theSP );                                //  Add to canvas
//    theSP.getViewport().add( theOutput );           //  In TextArea
//    rootWindow.setVisible( true );                  // Make visible

    GridPane buttonPane = new GridPane(); // button Pane
    buttonPane.addColumn(0, theBtPicked);
    buttonPane.setVgap(20); // Vertical Spacing

    GridPane infoPane = new GridPane();
    infoPane.addColumn(0, theAction, theSP);
    infoPane.setVgap(20);

    HBox root = new HBox();
    root.setId("Pick");
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList rootList = root.getChildren(); // retrieving the observable list of the root pane
    rootList.addAll(buttonPane, infoPane); // Adding all the nodes to the observable list


    // Set the Size of the GridPane
    root.setMinSize(400, 300);


    Scene scene = new Scene(root);  // Create the Scene
    scene.getStylesheets().add("catShop.css");
    stage.setScene(scene); // Add the scene to the Stage
  }

  public void setController( PickController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
    PickModel model  = (PickModel) modelC;
    String        message = (String) arg;
    Platform.runLater(()->theAction.setText(message));

   // theAction.setText( message );
    
    Basket basket =  model.getBasket();
    if ( basket != null )
    {
      //https://stackoverflow.com/questions/21083945/how-to-avoid-not-on-fx-application-thread-currentthread-javafx-application-th
      Platform.runLater(()->theOutput.setText( basket.getDetails() ) );

      //theOutput.setText( basket.getDetails() );
    } else {
      Platform.runLater(()->theOutput.setText(""));

      //theOutput.setText("");
    }
  }

}
