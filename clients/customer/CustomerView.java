package clients.customer;

import javafx.collections.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import middle.MiddleFactory;
import middle.StockReader;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class CustomerView implements Observer
{
  class Name                              // Names of buttons
  {
    public static final String CHECK  = "Check";
    public static final String CLEAR  = "Clear";

  }

  private static final int H = 330;       // Height of window pixels
  private static final int W = 430;       // Width  of window pixels

  private final Label theAction  = new Label();
 // private final Label theInputName = new Label();
  private final TextField  theInput   = new TextField();
  private final TextArea   theOutput  = new TextArea();
  private final ScrollPane theSP = new ScrollPane();
  private final Button     theBtCheck = new Button( Name.CHECK );
  private final Button     theBtClear = new Button( Name.CLEAR );

  private ImageView thePicture = new ImageView();

  private StockReader theStock   = null;
  private CustomerController cont= null;


  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen
   * @param y     y-cordinate of position of window on screen
   */

  public CustomerView(Stage stage, MiddleFactory mf, int x, int y )
  {

    try                                             //
    {
      theStock  = mf.makeStockReader();             // Database Access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }

    stage.setWidth( W ); // Set Window Size
    stage.setHeight( H );
    stage.setX( x );  // Set Window Position
    stage.setY( y );

    theBtCheck.setPrefSize( 80, 40 ); // Check Button Size
    theBtCheck.setOnAction(event -> cont.doCheck(theInput.getText()));
    theBtCheck.setTranslateY(10);
    theBtCheck.setTranslateX(10);

    theBtClear.setPrefSize( 80, 40 ); // Clear Button Size
    theBtClear.setOnAction(event -> cont.doClear());
    theBtClear.setTranslateY(180);
    theBtClear.setTranslateX(10);

    theAction.setPrefSize(270, 30);
    theAction.setText("");
    theAction.setTranslateY(5);
    theAction.setTranslateX(100);

    thePicture.setFitWidth( 100 );   // Picture area
    thePicture.setFitHeight( 100 );
    thePicture.setTranslateY(70);
    thePicture.setTranslateX(10);

    theInput.setPrefSize( 220, 35 );
    theInput.setText("");     // Blank
    theInput.setTranslateY(30);
    theInput.setTranslateX(110);

    theSP.setPrefSize(260,160);
    theSP.setTranslateY(75);
    theSP.setTranslateX(110);

    theOutput.setText( "" );
    theOutput.prefHeight(200);
    theSP.setContent(theOutput);

      Pane pane = new Pane();
      pane.getChildren().add(theBtCheck);
      pane.getChildren().add(theBtClear);
      pane.getChildren().add(theAction);
      pane.getChildren().add(theSP);
      pane.getChildren().add(theInput);
      pane.getChildren().add(thePicture);

    Scene scene = new Scene(pane);  // Create the Scene
      scene.getStylesheets().add("catShop.css");
      pane.setId("Customer");
      stage.setScene(scene); // Add the scene to the Stage

    theInput.requestFocus();  // Focus is here
  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( CustomerController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args
   */

  public void update( Observable modelC, Object arg )
  {
    CustomerModel model  = (CustomerModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );

    Image image = model.getPicture();  // Image of product
    if ( image == null )

      {
          System.out.println("IMAGE NULL");
      thePicture.setImage(null);                 // Clear picture
    } else {
      thePicture.setImage(image);             // Display picture
    }
    theOutput.setText( model.getBasket().getDetails() );
    theInput.requestFocus();               // Focus is here
  }
}
