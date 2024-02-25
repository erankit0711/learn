package Com.First.ecommerce.util;

import Com.First.ecommerce.address.domain.Address;
import Com.First.ecommerce.order.Model.Order;
import Com.First.ecommerce.product.Model.Product;
import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.domain.UserDetail;

import java.util.Random;


public class IdGenerator {

    public static String generate(Object object){
        long currentTimeMillis = System.currentTimeMillis();
        String prefixId = "";
        String suffixId = "";
        String fullId = "";
        if(object instanceof User){
            prefixId ="USR";
        }else if (object instanceof UserDetail){
            prefixId = "USD";
        }else if (object instanceof Order){
            prefixId = "ORD";
        } else if (object instanceof Address) {
            prefixId = "ADD";
        } else if (object instanceof Product) {
            prefixId = "PRO";
        } else {
            prefixId = "OTH";
        }
        suffixId = String.valueOf(currentTimeMillis).substring(3) + getRandomDigits(4);
        fullId = prefixId + suffixId;
        return fullId;
    }

    private static String getRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
