package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.*;
import com.codecool.shop.views.*;

import java.util.Currency;
import java.util.Scanner;

public class MenuController {
    private static Cart cart = new Cart();
    private Scanner scanner = new Scanner(System.in);
    private ProductCategoryView categoryView = new ProductCategoryView();
    private ProductView productView = new ProductView();
    private ProductCategoryDaoSqlite categoryDao = new ProductCategoryDaoSqlite();
    private SupplierDaoSqlite supplierDao = new SupplierDaoSqlite();
    private SupplierView supplierView = new SupplierView();
    private ProductDaoSqlite productDao = new ProductDaoSqlite();
    private CartView cartView = new CartView();
    private static Boolean quitSearchMenu = true;


    public void mainProgram() {
        Boolean quitProgram = false;
        Integer userChoice;
        MenuView.displayWelcome();
        getUserCurrency();
        while (!quitProgram) {
            MenuView.displayMain();
            userChoice = getUserChoice();
            switch (userChoice) {
                case 1:
                    productMenu();
                    break;
                case 2:
                    cartMenu();
                    break;
                case 3:
                    searchMenu();
                    break;
                case 4:
                    getUserCurrency();
                    break;
                case 0:
                    quitProgram = true;
                    break;
                default:
                    System.out.print("Wrong value!\n");
                    break;
            }
        }
    }

    private void getUserCurrency() {
        MenuView.flashMessage(" Please choose your currency: ");
        String[] currencies = new String[]{"PLN", "USD", "PHP", "GBP", "EUR"};
        int i = 1;
        for (;i<currencies.length + 1;i++){
            System.out.println(i + "." + " " + currencies[i-1]);
        }
        Integer currencyChoice = getUserChoice();

        while (currencyChoice < 0 || currencyChoice > currencies.length + 1){
            currencyChoice = getUserChoice("Incorrect input, please try again");
        }
        String chosenCurrency = currencies[currencyChoice - 1];
        Product.changeBaseCurrency(Currency.getInstance(chosenCurrency));
        MenuView.flashMessage("Prices are now shown in " + chosenCurrency);
    }

    private Integer getUserChoice() {
        System.out.print("Choice: ");
        if (scanner.hasNextInt())
            return scanner.nextInt();
        scanner.next();
        return -1;
    }

    private Integer getUserChoice(String msg){
        MenuView.flashMessage(msg);
        return getUserChoice();
    }

    private void productMenu(){
        Boolean quitProductMenu = false;
        while (!quitProductMenu) {
            MenuView.displayProducts();
            Integer choice = getUserChoice();
            if (choice == 0) {
                quitProductMenu = true;
            } else if(choice == 1){
                ProductController.getInstance().getAllProducts();
            } else if(choice == 2){
                showProductsByCategory();
            } else if(choice == 3) {
                showProductsBySupplier();
            }
            if (choice > 0 && choice < 4) {
                backOrAddToBasket();
            }
        }
    }

    private void showProductsByCategory(){
        ProductCategoryController.getInstance().getAllProductCategories();

        Integer categoryChoice = getUserChoice("Please enter product category id to see products");
        ProductCategory chosenCat = categoryDao.find(categoryChoice);
        while (chosenCat == null){
            categoryChoice = getUserChoice("Error, incorrect category id, please try again");
            chosenCat = categoryDao.find(categoryChoice);
        }
        MenuView.flashMessage("\nDisplaying products from category:");
        categoryView.showTableHead();
        categoryView.displayOne(chosenCat);
        MenuView.flashMessage("\nPRODUCTS:");
        ProductController.getInstance().getByProductCategory(chosenCat);
    }

    private void showProductsBySupplier(){
        SupplierController.getInstance().getAllSuppliers();
        Integer supplierChoice = getUserChoice("Please enter product supplier id to see products");
        Supplier chosenSup = supplierDao.find(supplierChoice);
        while (chosenSup == null){
            supplierChoice = getUserChoice("Error, incorrect supplier id, please try again");
            chosenSup = supplierDao.find(supplierChoice);
        }
        MenuView.flashMessage("\nDisplaying products from supplier:");
        supplierView.showTableHead();
        supplierView.displayOne(chosenSup);
        MenuView.flashMessage("\nPRODUCTS:");
        ProductController.getInstance().getBySupplier(chosenSup);
    }

    private void backOrAddToBasket(){
        MenuView.backOrAddToBasket();
        Integer actionChoice = getUserChoice();
        if (actionChoice == 1){
            addToBasketById();
        }
    }

    private void cartMenu() {
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

    private void showProductsByName() {
        MenuView.flashMessage("Please insert product name: ");
        String userProductSearch = scanner.next();
        MenuView.flashMessage("\nFOUND PRODUCTS CONTAINING YOUR PRODUCT NAME:");
        ProductController.getInstance().getProductsByName(userProductSearch);
    }

    private void searchMenu(){
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

    private Integer getQuantity(){
        Integer quantity = getUserChoice("How many items would you like to purchase?");
        while (quantity<1){
            quantity = getUserChoice("Error, quantity has to be positive integer! Please try again!");
        }
        return quantity;
    }
}