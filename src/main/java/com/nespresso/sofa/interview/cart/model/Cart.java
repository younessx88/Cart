package com.nespresso.sofa.interview.cart.model;

import static java.util.UUID.randomUUID;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Cart implements Serializable {

    private UUID id;
    private Map<String, Integer> products;


    public Cart() {
        this(randomUUID());
        this.products = new HashMap<String, Integer>();
    }

    public Cart(UUID id) {
        this.id = id;
        this.products = new HashMap<String, Integer>();
    }


    public Cart(Map<String, Integer> products) {
        this(randomUUID());
        this.products = products;
    }

    public UUID getId() {
        return this.id;
    }

    public Map<String, Integer> getProducts() {
        return this.products;
    }

    public String toString() {
        return "Cart {id: " + this.getId() + ", products: " + this.getProducts().toString() + "}";
    }


}
