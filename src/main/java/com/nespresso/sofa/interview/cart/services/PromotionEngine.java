package com.nespresso.sofa.interview.cart.services;

import java.util.HashMap;
import java.util.Map;

import com.nespresso.sofa.interview.cart.model.Cart;

/**
 * If one or more product with code "1000" is purchase, ONE product with code 9000 is offer
 * For each 10 products purchased a gift with code 7000 is offer.
 */
public class PromotionEngine {

    public static final String PRODUCT_WITH_PROMOTION = "1000";
    public static final String PROMOTION = "9000";
    public static final String GIFT = "7000";

    public Cart apply(Cart cart) {

        Map<String, Integer> products = new HashMap<>(cart.getProducts());

        int nbOfProducts = getQuantity(cart);

        if (products.containsKey(PRODUCT_WITH_PROMOTION)) {
            products.put(PROMOTION, 1);
        } else products.remove(PROMOTION);

        if (nbOfProducts >= 10) {
            int quantity = nbOfProducts / 10;
            products.put(GIFT, quantity);
        }

        if (nbOfProducts < 10) {
            products.remove(GIFT);
        }

        cart.setAllProducts(products);

        return cart;
    }

    private int getQuantity(Cart cart) {
        int result = 0;
        for (int value : cart.getProducts().values()) {
            result += value;
        }
        return result;
    }
}

