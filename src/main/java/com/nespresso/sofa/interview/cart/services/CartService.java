package com.nespresso.sofa.interview.cart.services;

import java.util.Map;
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
        if(quantity<=0) return false;
        else {
            final Cart cart = cartStorage.loadCart(cartId);
            Map<String,Integer> products=cart.getProducts();
            if(products.containsKey(productCode)){
                int oldQuantity= cart.getProducts().get(productCode);
                cart.getProducts().put(productCode,oldQuantity+quantity);
                return cartStorage.saveCart(cart)!=null;

            }
           else {
                cart.getProducts().put(productCode,quantity);
                return cartStorage.saveCart(cart)!=null;
            }
        }

        //return cart!=null;
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
        if(quantity==0){
            cart.getProducts().remove(productCode);
            return true;
        }
        else if(cart.getProducts().containsKey(productCode)) {
            if(cart.getProducts().get(productCode)==quantity) return false;
            else {
                int newQuantity=cart.getProducts().get(productCode)+quantity;
                if(quantity<=0){
                    cart.getProducts().remove(productCode);
                    return true;
                }
            }


        }

        cart.getProducts().put(productCode,quantity);
        return cartStorage.saveCart(cart)!=null;

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

    /**
     * Return the card with the corresponding ID
     *
     * @param cartId
     * @return
     */
    public String toString(UUID cartId) {
        return cartStorage.loadCart(cartId).toString();
    }
}
