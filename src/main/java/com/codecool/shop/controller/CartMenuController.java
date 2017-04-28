package com.codecool.shop.controller;

import com.codecool.shop.model.Payment;
import com.codecool.shop.views.MenuView;


public class CartMenuController extends BaseMenuController{

    public void runMenu() {
        Boolean quitCartMenu = false;
        while (!quitCartMenu) {
            MenuView.displayCart();
            Integer choice = getUserChoice();

            if (choice > 0 & choice < 4) {
                cartView.displayCart(cart);
            }
            if (choice == 0) {
                quitCartMenu = true;
            } else if (choice == 2) {
                changeQuantity();
            } else if (choice == 3){
                removeFromCart();
            } else if (choice == 4) {
                MenuView.displayCart();
                checkoutProcess();
            }
        }
    }

    private void changeQuantity(){
        editCart(false);
    }

    private void removeFromCart(){
        editCart(true);
    }

    private void checkoutProcess() {
        MenuView.flashMessage("Your cart: ");
        cartView.displayCart(cart);
        if (cart.size() < 1) {
            return;
        }
        MenuView.flashMessage("0 - back, 1 - payment");
        Integer choice = getUserChoice();
        if (choice == 1) {
            paymentProcess();
        }
    }
    private void paymentProcess() {
        MenuView.flashMessage("Enter your name:");
        String name = scanner.next();
        MenuView.flashMessage("Enter your card number:");
        String cardNumber = scanner.next();
        Payment payment = new Payment(name, cardNumber);
        if (payment.isValid()) {
            MenuView.flashMessage("Payment successful!");
        } else {
            MenuView.flashMessage("Something went wrong, try again!");
        }
    }

    private void editCart(Boolean remove){
        if (cart.size() < 1) {
            return;
        }
        Integer idChoice = getUserChoice("Please enter id of product you want to edit");
        Integer cartItemIndex = cart.getIndexIfExists(idChoice);
        if (cartItemIndex != null){
            if (remove){
                cart.remove(cartItemIndex);
                MenuView.flashMessage("Item successfully deleted");
            } else {
                Integer quantity = getQuantity();
                cart.changeQuantity(cartItemIndex, quantity);
                MenuView.flashMessage("Quantity successfully updated");
            }

        } else {
            MenuView.flashMessage("There's no item with id " + idChoice + " in your cart!");
        }
    }
}
