package DenisMogilevsky;

import java.util.Comparator;

public class ProductAmountComparator implements Comparator<Seller> {
    @Override
    public int compare(Seller seller1, Seller seller2){
        int products1 = seller1.getCurrentProductIndex();
        int products2 = seller2.getCurrentProductIndex();
        if(products1 == products2){
            return 0;
        } else if (products1 > products2) {
            return 1;
        }
        return -1;
    }
}
