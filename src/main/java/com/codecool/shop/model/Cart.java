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

    public void remove(int cartItemIndex){
        cartItems.remove(cartItemIndex);
    }

    public void changeQuantity(int cartItemIndex, int newQuantity){
        cartItems.get(cartItemIndex).setQuantity(newQuantity);
    }

    public Float getSum(){
        Float sum = 0f;
        for (CartItem i: cartItems){
            System.out.println(i.getConvertedCost());
            sum += i.getConvertedCost();
        }
        return sum;
    }

    @Override
    public Iterator<CartItem> iterator() {
        return cartItems.iterator();
    }

    }

