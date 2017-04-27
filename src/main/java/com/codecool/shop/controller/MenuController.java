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


    public static void mainProgram() {
        Integer userChoice = null;
        while (quitProgram) {
            try {
                MenuView.displayMain();
                userChoice = getUserChoice();
            } catch (InputMismatchException e) {
                read.nextInt();
            }
            switch (userChoice) {
                case 1:
                    while (quitProductMenu) {
                        MenuView.displayProducts();
                        Integer choice = getUserChoice();
                        if (choice == 0) {
                           quitProductMenu = false;
                        }
                    }
                    break;
                case 2:
                    while (quitCartMenu) {
                        MenuView.displayCart();
                        Integer choice = getUserChoice();
                        if (choice == 0) {
                            quitCartMenu = false;
                        }
                    }
                    break;
                case 3:
                    while (quitSearchMenu) {
                        MenuView.displaySearch();
                        Integer choice = getUserChoice();
                        if (choice == 0) {
                            quitSearchMenu = false;
                        }
                    }
                    break;
                case 0:
                    quitProgram = false;
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

}