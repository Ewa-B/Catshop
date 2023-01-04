package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Write a description of class BetterBasket here.
 * 
 * @author  Ewa Bancerz
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable, Comparator<Product> {
  private static final long serialVersionUID = 1L;

  @Override
  public boolean add(Product product) {
    // loop through the existing products
    for (Product p : this){
      if (product.getProductNum().equals(p.getProductNum())){
        // if found -> update the quantity
        p.setQuantity(p.getQuantity()+ product.getQuantity());
        return (true);
      }
    }
    // if not found -> add new product
    super.add(product);
    Collections.sort(this,this);
    return (true);
  }

  @Override
  public int compare(Product p1, Product p2) {
    return p1.getProductNum().compareTo(p2.getProductNum());
  }

  @Override
  public boolean remove(Product p) {
    for (Product product : this){
      System.out.println(product.getQuantity());
      if(p.getQuantity() >= 1){
        p.setQuantity(p.getQuantity()-1);

      }
    }
    Collections.sort(this,this);
    return true;
  }
}
