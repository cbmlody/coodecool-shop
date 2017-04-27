package com.codecool.shop.model;


import java.util.ArrayList;
import java.util.Iterator;

public class Cart implements Iterable<CartItem>{
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public void add(Product product, Integer quantity){
        Integer index = getIndexIfExists(product);
        if (index != null){
            cartItems.get(index).addToQuantity(quantity);
        } else {
            cartItems.add(new CartItem(product, quantity));
        }
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

    public int size(){
        return cartItems.size();
    }

    public Float getSum(){
        Float sum = 0f;
        for (CartItem i: cartItems){
            System.out.println(i.getConvertedCost());
            sum += i.getConvertedCost();
        }
        return sum;
    }

    private Integer getIndexIfExists(Product product){
        for (int i=0; i< size(); i++){
            if (cartItems.get(i).getProduct().equals(product)){
                return i;
            }
        }
        return null;
    }

    public Integer getIndexIfExists(int productId){
        for (int i=0; i< size(); i++){
            if (cartItems.get(i).getProduct().getId() == productId){
                return i;
            }
        }
        return null;
    }

    @Override
    public Iterator<CartItem> iterator() {
        return cartItems.iterator();
    }

    }

