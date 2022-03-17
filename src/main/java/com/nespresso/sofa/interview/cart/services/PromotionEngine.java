package com.nespresso.sofa.interview.cart.services;

import com.nespresso.sofa.interview.cart.model.Cart;

/**
 * If one or more product with code "1000" is purchase, ONE product with code 9000 is offer
 * For each 10 products purchased a gift with code 7000 is offer.
 */
public class PromotionEngine {

    public Cart apply(Cart cart) {
        if (!cart.getProducts().isEmpty()) {
            int quantity = 0;
            for (String key : cart.getProducts().keySet()) {
                quantity += cart.getProducts().get(key);
            }
            if (quantity >= 10) {
                int quantityResult = quantity / 10;
                cart.getProducts().put("7000", quantityResult);
            } else {
                cart.getProducts().remove("7000");
            }
            if (cart.getProducts().containsKey("1000")) {
                cart.getProducts().put("9000", 1);
            } else if (cart.getProducts().containsKey("9000")) {
                cart.getProducts().remove("9000");
            }
        }
        return cart;
    }
}
