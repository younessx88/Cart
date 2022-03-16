package com.nespresso.sofa.interview.cart.services;

import java.util.HashMap;
import java.util.Map;

import com.nespresso.sofa.interview.cart.model.Cart;

/**
 * If one or more product with code "1000" is purchase, ONE product with code 9000 is offer
 * For each 10 products purchased a gift with code 7000 is offer.
 */
public class PromotionEngine {

    public Cart apply(Cart cart) {
        Map<String, Integer> products = new HashMap<>(cart.getProducts());
        String GIFT = "7000";

        //calculate nb of products
        int nbOfProducts = 0;
        for (int value: products.values()) {
            nbOfProducts += value;
        }

        if(products.containsKey("1000")){
            products.put("9000", 1);
        }

        if(products.size() == 1 && products.containsKey("9000")){
                products.remove("9000");
        }



        //gift foreach 10 products
        if(nbOfProducts >= 10){
            int quantity = nbOfProducts / 10;
            products.put(GIFT, quantity);
        }

        //remove gift from products less than 10
        if(nbOfProducts < 10 && products.containsKey(GIFT)){
            products.remove(GIFT);
        }

        cart.setProducts(products);
        return cart;
    }
}
