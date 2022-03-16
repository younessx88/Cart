package com.nespresso.sofa.interview.cart.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.nespresso.sofa.interview.cart.model.Cart;


public class CartService {

    @Autowired
    private  PromotionEngine promotionEngine;
    @Autowired
    private  CartStorage cartStorage;


    /**
     * Add a quantity of a product to the cart and store the cart
     *
     * @param cartId      The cart ID
     * @param productCode The product code
     * @param quantity    Quantity must be added
     * @return True if card has been modified
     */
    public boolean add(UUID cartId, String productCode, int quantity) {
        boolean result = false;
        final Cart cart = cartStorage.loadCart(cartId);
        if (productCode != null && quantity > 0) {
            result = true;
            if (cart.getProducts().containsKey(productCode)) {
                cart.setProducts(productCode, cart.getProducts().get(productCode) + quantity);
            } else {
                cart.setProducts(productCode, quantity);
            }
            cartStorage.saveCart(cart);
        }
        return result;
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
        boolean result = true;
        final Cart cart = cartStorage.loadCart(cartId);
        if (quantity <= 0) {
            cart.getProducts().remove(productCode);
        } else if (productCode != null) {
            if (cart.getProducts().containsKey(productCode) && cart.getProducts().get(productCode) == quantity) {
                result = false;
            } else {
                cart.setProducts(productCode, quantity);
            }
            cartStorage.saveCart(cart);
        }
        return result;
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