package dbAccess;

/**
 * Implements Read access to the stock list
 * The stock list is held in a relational DataBase
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */

import catalogue.Product;
import debug.DEBUG;
import javafx.scene.image.Image;
import middle.StockException;
import middle.StockReader;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// There can only be 1 ResultSet opened per statement
// so no simultaneous use of the statement object
// hence the synchronized methods

// mySQL
//    no spaces after SQL statement ;

/**
  * Implements read only access to the stock database.
  */
public class StockR implements StockReader
{
  private Connection theCon    = null;      // Connection to database
  private Statement  theStmt   = null;      // Statement object

  /**
   * Connects to database
   * Uses a factory method to help setup the connection
   * @throws StockException if problem
   */
  public StockR()
         throws StockException
  {
    try
    {
      DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess();
      dbDriver.loadDriver();
    
      theCon  = DriverManager.getConnection
                  ( dbDriver.urlOfDatabase(), 
                    dbDriver.username(), 
                    dbDriver.password() );

      theStmt = theCon.createStatement();
      theCon.setAutoCommit( true );
    }
    catch ( SQLException e )
    {
      throw new StockException( "SQL problem:" + e.getMessage() );
    }
    catch ( Exception e )
    {
      throw new StockException("Can not load database driver.");
    }
  }


  /**
   * Returns a statement object that is used to process SQL statements
   * @return A statement object used to access the database
   */
  
  protected Statement getStatementObject()
  {
    return theStmt;
  }

  /**
   * Returns a connection object that is used to process
   * requests to the DataBase
   * @return a connection object
   */

  protected Connection getConnectionObject()
  {
    return theCon;
  }

  /**
   * Checks if the product exits in the stock list
   * @param pNum The product number
   * @return true if exists otherwise false
   */
  public String findProduct(String pNum){
    Map<String, String> products = new HashMap<>();
    products.put("classical acoustic guitar", "0001");
    products.put("43-inch 4k smart tv", "0002");
    products.put("smartwatch", "0003");
    products.put("headphones", "0004");
    products.put("laptop", "0005");
    products.put("apple imac", "0006");
    products.put("tablet", "0007");
    products.put("usb drive 128gb", "0008");

    for (Map.Entry<String, String > entry : products.entrySet()){

      if(entry.getKey().contains(pNum.toLowerCase())){
        String nr = entry.getValue();
        return nr;
      }
    }
    return pNum;
  }

  public synchronized boolean exists( String pNum )
         throws StockException
  {
    
    try
    {

      String nr = findProduct(pNum);

      ResultSet rs   = getStatementObject().executeQuery(
        "select price from ProductTable " +
        "  where  ProductTable.productNo = '" + nr + "'"
      );
      boolean res = rs.next();
      DEBUG.trace( "DB StockR: exists(%s) -> %s",
                    nr, ( res ? "T" : "F" ) );
      return res;
    } catch ( SQLException e )
    {
      throw new StockException( "SQL exists: " + e.getMessage() );
    }
  }

  /**
   * Returns details about the product in the stock list.
   *  Assumed to exist in database.
   * @param pNum The product number
   * @return Details in an instance of a Product
   */
  public synchronized Product getDetails( String pNum )
         throws StockException
  {
    try
    {
      String nr = findProduct(pNum);
      Product   dt = new Product( "0", "", 0.00, 0 );
      ResultSet rs = getStatementObject().executeQuery(
        "select description, price, stockLevel " +
        "  from ProductTable, StockTable " +
        "  where  ProductTable.productNo = '" + nr + "' " +
        "  and    StockTable.productNo   = '" + nr + "'"
      );
      if ( rs.next() )
      {
        dt.setProductNum( nr );
        dt.setDescription(rs.getString( "description" ) );
        dt.setPrice( rs.getDouble( "price" ) );
        dt.setQuantity( rs.getInt( "stockLevel" ) );
      }
      rs.close();
      return dt;
    } catch ( SQLException e )
    {
      throw new StockException( "SQL getDetails: " + e.getMessage() );
    }
  }

  /**
   * Returns 'image' of the product
   * @param pNum The product number
   *  Assumed to exist in database.
   * @return ImageIcon representing the image
   */
  public synchronized Image getImage(String pNum )
         throws StockException
  {
    String filename = "default.jpg";  
    try
    {
      String nr = findProduct(pNum);

      ResultSet rs   = getStatementObject().executeQuery(
        "select picture from ProductTable " +
        "  where  ProductTable.productNo = '" + nr + "'"
      );
      
      boolean res = rs.next();
      if ( res )
        filename = rs.getString( "picture" );
      rs.close();
    } catch ( SQLException e )
    {
      DEBUG.error( "getImage()\n%s\n", e.getMessage() );
      throw new StockException( "SQL getImage: " + e.getMessage() );
    }
    
    //DEBUG.trace( "DB StockR: getImage -> %s", filename );
    return new Image( filename );
  }


}
