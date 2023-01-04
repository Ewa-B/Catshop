package clients.backDoor;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import middle.MiddleFactory;
import middle.StockReadWriter;

//import javax.swing.*;
//import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class BackDoorView implements Observer
{
  private static final String RESTOCK  = "Add";
  private static final String CLEAR    = "Clear";
  private static final String QUERY    = "Query";

  private static final int H = 400;       // Height of window pixels
  private static final int W = 500;       // Width  of window pixels

  private final Label theAction  = new Label();
  private final TextField theInput   = new TextField();
  private final TextField  theInputNo = new TextField();
  private final TextArea theOutput  = new TextArea();
  private final ScrollPane theSP      = new ScrollPane();
  private final Button theBtClear = new Button( CLEAR );
  private final Button     theBtRStock = new Button( RESTOCK );
  private final Button     theBtQuery = new Button( QUERY );

  private StockReadWriter theStock     = null;
  private BackDoorController cont= null;

  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen
   * @param y     y-cordinate of position of window on screen
   */
  public BackDoorView(Stage stage, MiddleFactory mf, int x, int y )
  {
    try                                             //
    {
      theStock = mf.makeStockReadWriter();          // Database access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    stage.setWidth(W);
    stage.setHeight(H);
    stage.setX(x);
    stage.setY(y);
//    Container cp         = rpc.getContentPane();    // Content Pane
//    Container rootWindow = (Container) rpc;         // Root Window
//    cp.setLayout(null);                             // No layout manager
//    rootWindow.setSize( W, H );                     // Size of Window
//    rootWindow.setLocation( x, y );
//
//    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

    theBtQuery.setPrefSize(80, 40);    // Buy button
    theBtQuery.setOnAction(                   // Call back code
            e -> cont.doQuery( theInput.getText() ) );

    theBtRStock.setPrefSize(100, 40);   // Check Button
    theBtRStock.setOnAction(                  // Call back code
            e -> cont.doRStock( theInput.getText(),
                    theInputNo.getText() ) );

    theBtClear.setPrefSize( 100, 40 );    // Buy button
    theBtClear.setOnAction(                   // Call back code
            e -> cont.doClear() );


    theAction.setPrefSize( 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank

    theInput.setPrefSize(120, 40);        // Input Area
    theInput.setText("");                           // Blank

    theInputNo.setPrefSize( 120,40 );       // Input Area
    theInputNo.setText("0");                        // 0

    theSP.setPrefSize( 270, 160);          // Scrolling pane
    theOutput.setText( "" );    //  Blank
    theOutput.prefHeight(200);
    theSP.setContent(theOutput);

    GridPane buttonPane = new GridPane(); // button Pane
    buttonPane.addColumn(0, theBtQuery, theBtRStock, theBtClear);
    buttonPane.setVgap(20); // Vertical Spacing

    GridPane inputPane = new GridPane();
    inputPane.addRow(0, theInput, theInputNo);
    inputPane.setHgap(10);

    GridPane infoPane = new GridPane();
    infoPane.addColumn(0, theAction, inputPane, theSP);
    infoPane.setVgap(10);

    HBox root = new HBox();
    root.setId("BackDoor");
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList rootList = root.getChildren(); // retrieving the observable list of the root pane
    rootList.addAll(buttonPane, infoPane); // Adding all the nodes to the observable list


    // Set the Size of the GridPane
    root.setMinSize(400, 300);
    // Set style
//    String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5;" +
//            "-fx-border-radius: 5; -fx-border-color: purple; -fx-background-color: #b19cd9;";
//    String redButtonStyle = "-fx-background-radius: 1em; -fx-background-color: red; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
//    String blueButtonStyle = "-fx-background-radius: 1em; -fx-background-color: blue; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
//    String brownButtonStyle = "-fx-background-radius: 1em; -fx-background-color: brown; -fx-text-fill: white; -fx-font-family: 'Calibri'; -fx-font-weight: bolder; -fx-font-size: 14px";
//    String inputStyle = "-fx-background-color:lightgreen; -fx-font-family: Calibri; -fx-font-size: 16px";
//    String richAreaStyle = "-fx-control-inner-background:lightgreen; -fx-font-family: Calibri; -fx-font-size: 16px";
//    String labelStyle = "-fx-font-family: Calibri; -fx-font-size: 14px; -fx-font-weight: bolder;";

//    root.setStyle(rootStyle);
//    theBtQuery.setStyle(redButtonStyle);
//    theBtRStock.setStyle(blueButtonStyle);
//    theBtClear.setStyle(brownButtonStyle);
//    theAction.setStyle(labelStyle);
//    theOutput.setStyle(richAreaStyle);
//    theInput.setStyle(inputStyle);
//    theInputNo.setStyle(inputStyle);

    Scene scene = new Scene(root);  // Create the Scene
    scene.getStylesheets().add("catShop.css");
    stage.setScene(scene); // Add the scene to the Stage

    theInput.requestFocus();
  }

  public void setController( BackDoorController c )
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
    BackDoorModel model  = (BackDoorModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );

    theOutput.setText( model.getBasket().getDetails() );
    theInput.requestFocus();
  }

}