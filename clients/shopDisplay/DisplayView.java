package clients.shopDisplay;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import middle.MiddleFactory;
import middle.OrderException;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * The visual display seen by customers (Change to graphical version)
 * Change to a graphical display
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */
public class DisplayView implements Observer
{
  private static final long serialVersionUID = 1L;
 // private Font font = new Font("Monospaced",Font.BOLD,24);
  private int H = 400;         // Height of window
  private int W = 500;         // Width  of window
  private String textToDisplay = "";
  private DisplayController cont= null;
  private Canvas canvas = new Canvas(W, H);
  private Pane theRoot;

  /**
   * Construct the view
   * @param stage   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-coordinate of position of window on screen
   * @param y     y-coordinate of position of window on screen
   */

  public DisplayView(Stage stage, MiddleFactory mf, int x, int y )
  {
    stage.setWidth(W);
    stage.setHeight(H);
    stage.setY(y);
    stage.setX(x);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setLineWidth(5.0);
    theRoot = new Pane();
    theRoot.setId("Display");
//    String rootStyle = "-fx-padding: 10;-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-insets: 5;" +
//            "-fx-border-radius: 5; -fx-border-color: purple; -fx-background-color: #b19cd9;";
//
//    theRoot.setStyle(rootStyle);
    // Add the Canvas to the Pane
    theRoot.getChildren().add(canvas);
    // Create the Scene
    Scene scene = new Scene(theRoot);
    scene.getStylesheets().add("catShop.css");

      // Add the Scene to the Stage
    stage.setScene(scene);
    // Display the Stage
    stage.show();
//    Container cp         = rpc.getContentPane();    // Content Pane
//    Container rootWindow = (Container) rpc;         // Root Window
//    cp.setLayout( new BorderLayout() );             // Border N E S W CENTER
//    rootWindow.setSize( W, H );                     // Size of Window
//    rootWindow.setLocation( x, y );                 // Position on screen
//    rootWindow.add( this, BorderLayout.CENTER );    //  Add to rootwindow
//
//    rootWindow.setVisible( true );                  // Make visible
  }


  public void setController( DisplayController c )
  {
    cont = c;
  }

  /**
   * Called to update the display in the shop
   */
  @Override
  public void update( Observable aModelOfDisplay, Object arg )
  {
    // Code to update the graphical display with the current
    //  state of the system
    //  Orders awaiting processing
    //  Orders being picked in the 'warehouse.
    //  Orders awaiting collection

    try
    {
      Map<String, List<Integer> > res =
              ( (DisplayModel) aModelOfDisplay ).getOrderState();

      textToDisplay =
              "Orders in system" + "\n" +
                      "Waiting        : " + listOfOrders( res, "Waiting" ) +
                      "\n"  +
                      "Being picked   : " + listOfOrders( res, "BeingPicked" ) +
                      "\n"  +
                      "To Be Collected: " + listOfOrders( res, "ToBeCollected" );
    }
    catch ( OrderException err )
    {
      textToDisplay = "\n" + "** Communication Failure **";
    }
    String lines[] = textToDisplay.split(("\n"));
    GraphicsContext gc = canvas.getGraphicsContext2D();
    //background color
    gc.setFill(new Color(1, 0.911, 0.847, 1));
      // gc.setFill(new javafx.scene.paint.Color(0.69, 0.611, 0.847, 1));
    //fill the rectangle with above color
      gc.fillRect(10, 10, W-40, H-60);

      //Text color
    gc.setFill(Color.DEEPPINK);
    gc.setFont(Font.font(20));
    for ( int i=0; i<lines.length; i++ )
    {
      gc.fillText( lines[i], 50, 40 + 40*i, 1000 );
    }
   // repaint();                            // Draw graphically
  }


//  @Override
//  public void update( Graphics g )        // Called by repaint
//  {                                       //
//    drawScreen( (Graphics2D) g );         // Draw information on screen
//  }

  /**
   * Redraw the screen double buffered
   * @param g Graphics context
   */
//  @Override
//  public void paint( Graphics g )         // When 'Window' is first
//  {                                       //  shown or damaged
//    drawScreen( (Graphics2D) g );         // Draw information on screen
//  }
//
//  private Dimension     theAD;           // Alternate Dimension
//  private BufferedImage theAI;           // Alternate Image
//  private Graphics2D    theAG;           // Alternate Graphics
//
//  public void drawScreen( Graphics2D g )  // Re draw contents
//  {                                         //  allow resize
//    Dimension d    = getSize();             // Size of image
//
//    if (  ( theAG == null )           ||
//            ( d.width  != theAD.width ) ||
//            ( d.height != theAD.height )   )
//    {                                       // New size
//      theAD = d;
//      theAI = (BufferedImage) createImage( d.width, d.height );
//      theAG = theAI.createGraphics();
//    }
//    drawActualScreen( theAG );            // draw
//    g.drawImage( theAI, 0, 0, this );     // Now on screen
//  }
//
//  /**
//   * Redraw the screen
//   * @param g Graphics context
//   */
//
//  public void drawActualScreen( Graphics2D g )  // Re draw contents
//  {
//    g.setPaint( Color.white );            // Paint Colour
//    W = getWidth(); H = getHeight();      // Current size
//
//    g.setFont( font );
//    g.fill( new Rectangle2D.Double( 0, 0, W, H ) );
//
//    // Draw state of system on display
//    String lines[] = textToDisplay.split("\n");
//    g.setPaint( Color.black );
//    for ( int i=0; i<lines.length; i++ )
//    {
//      g.drawString( lines[i], 0, 50 + 50*i );
//    }
//
//  }

  /**
   * Return a string of order numbers
   * @param map Contains the current state of the system
   * @param key The key of the list requested
   * @return As a string a list of order numbers.
   */
  private String listOfOrders( Map<String, List<Integer> > map, String key )
  {
    String res = "";
    if ( map.containsKey( key ))
    {
      List<Integer> orders = map.get(key);
      for ( Integer i : orders )
      {
        res += " " + i;
      }
    } else {
      res = "-No key-";
    }
    return res;
  }
}
