package com.codecool.shop.model;


import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public void add(Product product, Integer quantity){
        cartItems.add(new CartItem(product, quantity));
    }

    public void add(Product product){
        add(product, 1);
    }

    //TODO: Not sure if it should be inner
    private class CartItem {
        Product product;
        Integer quantity;

        CartItem(Product product, Integer quantity){
            this.product = product;
            this.quantity = quantity;
        }
    }

//    private TreeMap<Product, Integer> productMap = new TreeMap<>();
//
//    public void add(Product product, Integer quantity){
//        if (productMap.containsKey(product)){
//            productMap.put(product, productMap.get(product) + quantity);
//        } else {
//            productMap.put(product, quantity);
//        }
//    }
//
//    public void add(Product product){
//        add(product, 1);
//    }
//
//    @Override
//    public Iterator<Product> iterator() {
//        Set<Product> cartProducts= productMap.keySet();
//        return cartProducts.iterator();
//    }
}
