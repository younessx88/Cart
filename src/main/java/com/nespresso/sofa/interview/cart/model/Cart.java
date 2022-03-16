package com.nespresso.sofa.interview.cart.model;

import static java.util.UUID.randomUUID;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public final class Cart implements Serializable {

    private final UUID id;
    private Map<String , Integer> products = new Hashtable<>();

    public Cart() {
        this(randomUUID());
    }

    public Cart(UUID id) {
        this.id = id;
    }

    public void setProducts(String product, final Integer quantity) {
        this.products.put(product , quantity);
    }

    public Cart(Map<String, Integer> products) {
        this.id = randomUUID();
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, Integer> getProducts() {

        return this.products;
    }


    @Override
    public String toString() {
        return "Cart {" +
            "id: " + id +
            ", products: " + products +
            '}';
    }
}
