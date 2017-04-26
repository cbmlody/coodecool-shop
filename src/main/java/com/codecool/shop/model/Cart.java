package com.codecool.shop.model;


import java.util.ArrayList;
import java.util.Iterator;

public class Cart implements Iterable<CartItem>{
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public void add(Product product, Integer quantity){
        cartItems.add(new CartItem(product, quantity));
    }

    public void add(Product product){
        add(product, 1);
    }

    @Override
    public Iterator<CartItem> iterator() {
        return cartItems.iterator();
    }

    }

