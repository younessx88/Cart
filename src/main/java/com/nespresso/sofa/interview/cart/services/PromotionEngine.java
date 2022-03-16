package com.nespresso.sofa.interview.cart.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.nespresso.sofa.interview.cart.model.Cart;

/**
 * If one or more product with code "1000" is purchase, ONE product with code 9000 is offer
 * For each 10 products purchased a gift with code 7000 is offer.
 */
public class PromotionEngine {
    public Cart apply(Cart cart) {
        Map<String, Integer> temp = new HashMap<>(cart.getProducts());
        temp = addPromotion(temp);
        temp = addGift(temp);
        return new Cart(temp);
    }

    private Map<String,Integer> addPromotion(Map<String,Integer> products) {
        if (!products.containsKey("1000")) {
            products.remove("9000");
        } else {
            products.put("9000", 1);
        }
        return products;
    }

    private Integer maxValue(Map<String,Integer> products) {
        Map.Entry<String, Integer> maxEntry = Collections.max(products.entrySet(),
            (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e1.getValue()
                .compareTo(e2.getValue()));
        return maxEntry.getValue();
    }

    private Map<String,Integer> addGift(Map<String,Integer> products) {
        if (!products.isEmpty()) {
            Integer max = maxValue(products);
            if (max >= 10) {
                products.put("7000", max / 10);
            } else {
                products.remove("7000");
            }
        }
        return products;
    }
}
