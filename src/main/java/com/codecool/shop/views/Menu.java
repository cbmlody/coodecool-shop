package com.codecool.shop.views;

public final class Menu {
    private static final Menu MENU = new Menu();
    private Menu() {}
    public static Menu getInstance() {
        return MENU;
    }

    public static void displayMain() {
        System.out.println("MAIN MENU\n" +
                "  1. Show products\n" +
                "  2. Show Cart\n" +
                "  3. Search\n" +
                "  0. Exit\n");
    }

    public static void displayProducts() {
        System.out.println("PRODUCTS MENU\n" +
                "  1. Show all products" +
                "  1. Show product categories\n" +
                "  3. Show products from category\n" +
                "  0. Back\n");
    }

}
