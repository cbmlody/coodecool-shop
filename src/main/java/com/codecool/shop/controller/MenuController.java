package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.views.CartView;
import com.codecool.shop.views.MenuView;
import com.codecool.shop.views.ProductCategoryView;
import com.codecool.shop.views.SupplierView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    private static Cart cart = new Cart();
    private Scanner read = new Scanner(System.in);
    private ProductCategoryView categoryView = new ProductCategoryView();
    private ProductCategoryDaoSqlite categoryDao = new ProductCategoryDaoSqlite();
    private SupplierDaoSqlite supplierDao = new SupplierDaoSqlite();
    private SupplierView supplierView = new SupplierView();
    private ProductDaoSqlite productDao = new ProductDaoSqlite();
    private CartView cartView = new CartView();

    private static Boolean quitCartMenu = true;
    private static Boolean quitSearchMenu = true;


    public void mainProgram() {
        Boolean quitProgram = false;
        Integer userChoice = null;
        while (!quitProgram) {
            try {
                MenuView.displayMain();
                userChoice = getUserChoice();
            } catch (InputMismatchException e) {
                read.nextInt();
            }
            switch (userChoice) {
                case 1:
                    productMenu();
                    break;
                case 2:
                    while (quitCartMenu) {
                        MenuView.displayCart();
                        Integer choice = getUserChoice();
                        if (choice == 0) {
                            quitCartMenu = true;
                        }
                    }
                    break;
                case 3:
                    while (quitSearchMenu) {
                        MenuView.displaySearch();
                        Integer choice = getUserChoice();
                        if (choice == 0) {
                            quitSearchMenu = true;
                        }
                    }
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
    private Integer getUserChoice() {
        System.out.print("Choice: ");
        Scanner scanner = new Scanner(System.in);
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

    private void addToBasketById(){
        Integer productChoice = getUserChoice("Please enter product id to add products to cart");
        Product chosenProd = productDao.find(productChoice);
        while (chosenProd == null){
            productChoice = getUserChoice("Error, incorrect product id, please try again");
            chosenProd = productDao.find(productChoice);
        }
        Integer quantity = getUserChoice("How many items would you like to purchase?");
        while (quantity<1){
            quantity = getUserChoice("Error, quantity has to be positive integer! Please try again!");
        }
        cart.add(chosenProd, quantity);
        MenuView.flashMessage("Product added to cart! \nYour actual cart:");
        cartView.displayCart(cart);

    }

}