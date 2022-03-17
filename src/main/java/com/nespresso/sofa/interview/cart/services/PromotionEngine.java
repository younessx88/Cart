package com.nespresso.sofa.interview.cart.services;

import java.util.Map.Entry;

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
        Cart oldCart = new Cart(cart.getProducts());

        //Promotion Cannot be purchased
        cart.getProducts().remove(PROMOTION);
        if (oldCart.getProducts().containsKey(PRODUCT_WITH_PROMOTION)) cart.getProducts().put(PROMOTION, 1);

        //Gift Cannot be purchased
        cart.getProducts().remove(GIFT);
        int count = 0;
        for (Entry<String, Integer> product : oldCart.getProducts().entrySet()) count += product.getValue();
        if (count>=10) cart.getProducts().put(GIFT, (count/10));

        return cart;
    }
}
