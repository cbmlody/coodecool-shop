package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.Scanner;

public abstract class BaseMenuController {
    Scanner scanner = new Scanner(System.in);
    static Cart cart = new Cart();
    private ProductDaoSqlite productDao = new ProductDaoSqlite();

    public abstract void runMenu();
    

    Integer getUserChoice(String msg){
        MenuView.flashMessage(msg);
        return getUserChoice();
    }

     Integer getQuantity(){
        Integer quantity = getUserChoice("How many items would you like to purchase?");
        while (quantity<1){
            quantity = getUserChoice("Error, quantity has to be positive integer! Please try again!");
        }
        return quantity;
    }

     void backOrAddToBasket(){
        MenuView.backOrAddToBasket();
        Integer actionChoice = getUserChoice();
        if (actionChoice == 1){
            addToBasketById();
        }
    }

    private void addToBasketById(){
        Integer productChoice = getUserChoice("Please enter product id to add products to cart");
        Product chosenProd = productDao.find(productChoice);
        while (chosenProd == null){
            productChoice = getUserChoice("Error, incorrect product id, please try again");
            chosenProd = productDao.find(productChoice);
        }
        Integer quantity = getQuantity();
        cart.add(chosenProd, quantity);
        MenuView.flashMessage("Product added to cart! \nYour actual cart:");
        cartView.displayCart(cart);
    }
}
