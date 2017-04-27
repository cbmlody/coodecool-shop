package com.codecool.shop.views;

public final class MenuView {
    private static final MenuView MENU = new MenuView();
    private MenuView() {}
    public static MenuView getInstance() {
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

    public static void displayCart() {
        System.out.println("CART MENU\n" +
                "  1. Show items in cart\n" +
                "  2. Change quantity\n" +
                "  3. Remove item from cart\n" +
                "  4. Checkout cart\n" +
                "  0. Back\n");
    }

    public static void displaySearch() {
        System.out.println("SEARCH MENU\n" +
                "  1. Search by product category\n" +
                "  2. Search by supplier\n" +
                "  0. Back\n");
    }

    public static void displayCheckout() {
        System.out.println("CHECKOUT\n" +
                "  1. Payment\n" +
                "  0. Back");
    }
}
