package com.codecool.shop.controller;

import com.codecool.shop.views.MenuView;

public class SearchMenuController extends BaseMenuController{
    public void runMenu(){
        Boolean quitSearchMenu = false;
        while (!quitSearchMenu) {
            MenuView.displaySearch();
            Integer choice = getUserChoice();
            if (choice == 0) {
                quitSearchMenu = true;
            } else if(choice == 1) {
                showProductsByName();
            }
            if (choice > 0 && choice < 2) {
                backOrAddToBasket();
            }
        }
    }

    private void showProductsByName() {
        MenuView.flashMessage("Please insert product name: ");
        String userProductSearch = scanner.next();
        MenuView.flashMessage("\nFOUND PRODUCTS CONTAINING YOUR PRODUCT NAME:");
        ProductController.getInstance().getProductsByName(userProductSearch);
    }



}
