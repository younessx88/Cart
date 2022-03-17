package com.nespresso.sofa.interview.cart.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.nespresso.sofa.interview.cart.model.Cart;

/**
 * If one or more product with code "1000" is purchase, ONE product with code 9000 is offer
 * For each 10 products purchased a gift with code 7000 is offer.
 */
public class PromotionEngine {

    public Cart apply(Cart cart) {
        if(!cart.getProducts().isEmpty()){
           Cart newCart=promotion(cart);
            return gift(newCart);

        }

        return cart;
    }

    private Cart promotion(Cart cart){
        int allProductsQuantity=0;
        for(String key: cart.getProducts().keySet()) {
            allProductsQuantity+=cart.getProducts().get(key);
        }
        if(allProductsQuantity>=10){
            cart=  addItem(cart,"7000",(allProductsQuantity/10));
        }
        else if(cart.getProducts().containsKey("7000")){
            cart= removeItem(cart,"7000");
        }
        return cart;
    }
    private Cart gift( Cart cart){
        if(cart.getProducts().containsKey("1000")){
            cart= addItem(cart,"9000",1);
        }
        else if(cart.getProducts().containsKey("9000")){
            if(cart.getProducts().size()==1){
                cart=removeItem(cart,"9000");
            }
            else  cart=  addItem(cart,"9000",1);

        }
        return cart;
    }
    private Cart removeItem(Cart cart,String code){
        Cart newCart=new Cart(cart.getId());
        for(String key: cart.getProducts().keySet()) {
            if(!key.equals(code)) newCart.getProducts().put(key,cart.getProducts().get(key));
        }
        return  newCart;
    }
    private Cart addItem(Cart cart,String code,Integer quantity){
        Cart newCart=new Cart(cart.getId());
        for(String key: cart.getProducts().keySet()) {
            newCart.getProducts().put(key,cart.getProducts().get(key));
        }
        newCart.getProducts().put(code,quantity);
        return  newCart;
    }
}
