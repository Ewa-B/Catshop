import catalogue.BetterBasket;
import catalogue.Product;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

class BetterBasketTest {
     @Test
    void testMergeAddProduct(){
         BetterBasket b = new BetterBasket();
         Product p1 = new Product("0001", "TV", 269.00, 1);
         Product p2 = new Product("0001", "TV", 269.00, 1);
         Product p3 = new Product("0002", "Radio", 29.99, 1);
         Product p4 = new Product("0002", "Radio", 29.99, 1);

         b.add(p1);
         b.add(p2);
          assertEquals(1, b.size());
          assertEquals(2, b.get(0).getQuantity());

          b.add(p3);
          assertEquals(2, b.size());
          b.add(p4);
          assertEquals(3, b.get(1).getQuantity());
    }

}
