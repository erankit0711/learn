package Com.First.ecommerce.util;

import Com.First.ecommerce.product.Model.Product;
import Com.First.ecommerce.user.domain.User;

import java.util.Random;


public class IdGenerator {

    public static String generate(Object object){
        long currentTimeMillis = System.currentTimeMillis();
        String id;
        if(object instanceof User){
            id ="1";
        }else if (object instanceof Product){
            id = "2";
        }else{
            id = "3";
        }
        id = id+String.valueOf(currentTimeMillis).substring(3) + getRandomDigits(4);
        return id;
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
