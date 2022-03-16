package com.nespresso.sofa.interview.cart.services;

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
        if(cart.getProducts().containsKey(PRODUCT_WITH_PROMOTION)) {
            cart.setProducts(PROMOTION,1);
        }
        return cart;
    }
}
