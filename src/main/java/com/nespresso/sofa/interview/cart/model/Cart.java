package com.nespresso.sofa.interview.cart.model;

import static java.util.UUID.randomUUID;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public final class Cart implements Serializable {

    private final UUID id;
    private Map<String, Integer> products;

    public Cart() {
        this(randomUUID());
    }

    public Cart(UUID id) {
        this.id = id;
        this.initCart();
    }

    public Cart(Map<String, Integer> products) {
        this.id = randomUUID();
        // products can be Immutable
        this.products = new HashMap<>(products);
    }

    private void initCart() {
        this.products = new HashMap<String, Integer>();
    }

    public UUID getId() {
        return id;
    }

    public Map<String, Integer> getProducts() {
        return this.products;
    }

    @Override
    public String toString() {
        return "Cart " +
            "{" +
                "id: " + this.id + ", " +
                "products: " + products +
            "}";
    }
}
