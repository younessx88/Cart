package com.nespresso.sofa.interview.cart.services;

import java.util.UUID;

import com.nespresso.sofa.interview.cart.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;

public class CartService {
    @Autowired
    private PromotionEngine promotionEngine;
    @Autowired
    private CartStorage cartStorage;

    public CartService() {
    }

    /*
    @Autowired
    public CartService(PromotionEngine promotionEngine, CartStorage cartStorage) {
        this.promotionEngine = promotionEngine;
        this.cartStorage = cartStorage;
    }*/

    /**
     * Add a quantity of a product to the cart and store the cart
     *
     * @param cartId      The cart ID
     * @param productCode The product code
     * @param quantity    Quantity must be added
     * @return True if card has been modified
     */
    public boolean add(UUID cartId, String productCode, int quantity) {
        final Cart cart = cartStorage.loadCart(cartId);
        if (quantity > 0) {
            cart.getProducts().merge(productCode, quantity, Integer::sum);
            promotionEngine.apply(cart);
            cartStorage.saveCart(cart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set a quantity of a product to the cart and store the cart
     *
     * @param cartId      The cart ID
     * @param productCode The product code
     * @param quantity    The new quantity
     * @return True if card has been modified
     */
    public boolean set(UUID cartId, String productCode, int quantity) {
        final Cart cart = cartStorage.loadCart(cartId);
        Integer value;
        if (quantity > 0) {
            value = cart.getProducts().put(productCode, quantity);
            if( value == null ){
                value = -1;
            }
        }else if (quantity == 0){
            value = cart.getProducts().remove(productCode);
        } else {
            value = cart.getProducts().remove(productCode);
        }
        promotionEngine.apply(cart);
        cartStorage.saveCart(cart);
        return value != null && value != quantity;
    }

    /**
     * Return the card with the corresponding ID
     *
     * @param cartId
     * @return
     */
    public Cart get(UUID cartId) {
        return cartStorage.loadCart(cartId);
    }
}
