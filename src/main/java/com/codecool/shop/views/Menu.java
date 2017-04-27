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

}
