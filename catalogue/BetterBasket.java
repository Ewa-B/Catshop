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
  public boolean add(Product pr1) {
    // loop through the existing products
    for (Product pr2 : this){
      if (pr1.getProductNum().equals(pr2.getProductNum())){
        // if found -> update the quantity
        pr2.setQuantity(pr2.getQuantity()+ pr1.getQuantity());
        return (true);
      }
    }
    // if not found -> add new product
    super.add(pr1);
    Collections.sort(this,this);
    return (true);
  }

  @Override
  public int compare(Product p1, Product p2) {
    return p1.getProductNum().compareTo(p2.getProductNum());
  }
}
