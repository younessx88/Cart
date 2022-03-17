package com.nespresso.sofa.interview.cart.services;

import java.util.UUID;

import com.nespresso.sofa.interview.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;

public class CartService {

    @Autowired
    private  PromotionEngine promotionEngine;
    @Autowired
    private  CartStorage cartStorage;

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
        Cart cart = get(cartId);
        Integer add = null;
        if (quantity!=0) {
            add = cart.getProducts().merge(productCode, quantity, Integer::sum);
            if (add <= 0){
                cart.getProducts().remove(productCode);
                add = null;
            }
        }
        cart = this.promotionEngine.apply(cart);
        cartStorage.saveCart(cart);
        return add !=null;
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
        Cart cart = get(cartId);
        Integer set;
        if (quantity > 0) {
            set = cart.getProducts().put(productCode, quantity);
            set = set == null ? -1 : set;
        }else if (quantity == 0){
            set = cart.getProducts().remove(productCode);
        } else {
            set = cart.getProducts().remove(productCode);
        }
        cart = this.promotionEngine.apply(cart);
        cartStorage.saveCart(cart);
        return set != null && set != quantity;
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
