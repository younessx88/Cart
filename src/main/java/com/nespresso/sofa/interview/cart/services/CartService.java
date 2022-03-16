package com.nespresso.sofa.interview.cart.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.nespresso.sofa.interview.cart.model.Cart;

public class CartService {

    @Autowired
    private PromotionEngine promotionEngine;
    @Autowired
    private CartStorage cartStorage;

    /**
     * Add a quantity of a product to the cart and store the cart
     *
     * @param cartId
     *     The cart ID
     * @param productCode
     *     The product code
     * @param quantity
     *     Quantity must be added
     * @return True if card has been modified
     */
    public boolean add(UUID cartId, String productCode, int quantity) {
        final Cart cart = cartStorage.loadCart(cartId);

        if (quantity > 0) {
            cart.getProducts().put(productCode, cart.getProducts().getOrDefault(productCode, 0) + quantity);
            cartStorage.saveCart(cart);
            return true;
        } else {
            return false;
        }
    }



    /**
     * Set a quantity of a product to the cart and store the cart
     *
     * @param cartId
     *     The cart ID
     * @param productCode
     *     The product code
     * @param quantity
     *     The new quantity
     * @return True if card has been modified
     */
    public boolean set(UUID cartId, String productCode, int quantity) {
        final Cart cart = cartStorage.loadCart(cartId);
        if (cart.getProducts().getOrDefault(productCode, 0) != quantity) {
            if (quantity > 0) {
                cart.getProducts().put(productCode, quantity);
                cartStorage.saveCart(cart);
                return true;
            } else if (quantity == 0) {
                cart.getProducts().remove(productCode);
                return true;
            } else {
                if(Math.abs(quantity)<cart.getProducts().getOrDefault(productCode, 0)){
                    cart.getProducts().put(productCode,cart.getProducts().getOrDefault(productCode, 0)-quantity);
                    return true;
                }else if(Math.abs(quantity)>=cart.getProducts().getOrDefault(productCode, 0)){
                    cart.getProducts().remove(productCode);
                    return true;
                }
            }
        }
        return false;
    }

        /**
         * Return the card with the corresponding ID
         *
         * @param cartId
         * @return
         */
        public Cart get (UUID cartId){
            return cartStorage.loadCart(cartId);
    }
}
