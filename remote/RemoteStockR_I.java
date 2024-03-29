package remote;

import catalogue.Product;
import javafx.scene.image.Image;
import middle.StockException;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Defines the RMI interface for read access to the stock object.
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */

public interface RemoteStockR_I
       extends Remote
{
  boolean   exists(String number)
            throws RemoteException, StockException;
  Product   getDetails(String number)
            throws RemoteException, StockException;
  Image getImage(String number)
            throws RemoteException, StockException;

}

