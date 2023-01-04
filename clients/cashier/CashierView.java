package clients.cashier;

import catalogue.Basket;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockReadWriter;

//import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


/**
 * View of the model
 * @author  M A Smith (c) June 2014
 */
public class CashierView implements Observer
{
  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private static final String CHECK  = "Check";
  private static final String BUY    = "Buy";
  private static final String BOUGHT = "Bought";

  private final Label theAction  = new Label();
  private final TextField theInput   = new TextField();
  private final TextArea theOutput  = new TextArea();
  private final ScrollPane theSP      = new ScrollPane();
  private final Button theBtCheck = new Button( CHECK );
  private final Button     theBtBuy   = new Button( BUY );
  private final Button     theBtBought= new Button( BOUGHT );

  private Button remove;

  private StockReadWriter theStock     = null;
  private OrderProcessing theOrder     = null;
  private CashierController cont       = null;

  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-coordinate of position of window on screen
   * @param y     y-coordinate of position of window on screen
   */

  public CashierView(Stage stage, MiddleFactory mf, int x, int y  )
  {
    try                                           // 
    {
      theStock = mf.makeStockReadWriter();        // Database access
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    stage.setWidth(W);
    stage.setHeight(H);
    stage.setY(y);
    stage.setX(x);

    theBtCheck.setPrefSize(100, 40);
    theBtCheck.setOnAction(event -> cont.doCheck(theInput.getText()));

    theBtBuy.setPrefSize( 100, 40 );      // Buy button
    theBtBuy.setOnAction(event -> cont.doBuy());

    theBtBought.setPrefSize(100, 40);   // Clear Button
    theBtBought.setOnAction(event -> cont.doBought());

    remove = new Button("Remove");
    remove.setOnAction(event -> cont.doRemove());

    theAction.setPrefSize(270, 20);       // Message area
    theAction.setText( "" );                        // Blank

    theInput.setPrefSize(270, 40);        // Input Area
    theInput.setText("");                           // Blank
    theInput.prefHeight(150);

    theSP.setPrefSize(270,160);
    theOutput.setText( "" );
    theSP.setContent(theOutput);//  Blank
                     // Focus is here
    GridPane buttonPane = new GridPane(); // button Pane
    buttonPane.addColumn(0, theBtCheck, theBtBuy, theBtBought, remove);
    buttonPane.setVgap(20); // Vertical Spacing

    GridPane inputBar = new GridPane();
    inputBar.addRow(0, theInput);
    inputBar.setHgap(10);


    GridPane infoPane = new GridPane();
    infoPane.addColumn(0, theAction,inputBar, theSP);
    infoPane.setVgap(10);

    HBox root = new HBox();
    root.setId("Cashier");
    root.setSpacing(10);   //Setting the space between the nodes of a root pane

    ObservableList<Node> rootList = root.getChildren(); // retrieving the observable list of the root pane
    rootList.addAll(buttonPane, infoPane); // Adding all the nodes to the observable list

    root.setMinSize(400, 300);


    Scene scene = new Scene(root);  // Create the Scene
    scene.getStylesheets().add("catShop.css");

    stage.setScene(scene); // Add the scene to the Stage

    theInput.requestFocus();
  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( CashierController c )
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
    CashierModel model  = (CashierModel) modelC;
    String      message = (String) arg;
    theAction.setText( message );
    Basket basket = model.getBasket();
    if ( basket == null ) {
      theOutput.setText("Customers order");
    }
    else {
      theOutput.setText(basket.getDetails());
    }

    theInput.requestFocus();               // Focus is here
  }

}
