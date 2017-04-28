package com.codecool.shop.views;

public final class MenuView {
    private static final MenuView MENU = new MenuView();
    private MenuView() {}
    public static MenuView getInstance() {
        return MENU;
    }

    public static void displayWelcome() {
        System.out.println("WELCOME TO CC ONLINE SHOP\n" +
                "  Please enter your currency (eg. PLN, USD, EUR): ");
    }

    public static void displayMain() {
        System.out.println("\nMAIN MENU\n" +
                "  1. Browse products\n" +
                "  2. Cart Menu\n" +
                "  3. Search\n" +
                "  0. Exit\n");
    }

    public static void displayProducts() {
        System.out.println("\nPRODUCTS MENU\n" +
                "  1. Show all products\n" +
                "  2. Show products by category\n" +
                "  3. Show products by supplier\n" +
                "  0. Back\n");
    }

    public static void displayCart() {
        System.out.println("\nCART MENU\n" +
                "  1. Show items in cart\n" +
                "  2. Change quantity\n" +
                "  3. Remove item from cart\n" +
                "  4. Checkout cart\n" +
                "  0. Back\n");
    }

    public static void displaySearch() {
        System.out.println("\nSEARCH MENU\n" +
                "  1. Search product by name\n" +
                "  0. Back\n");
    }

    public static void displayCheckout() {
        System.out.println("\nCHECKOUT\n" +
                "  1. Payment\n" +
                "  0. Back\n");
    }

    public static void backOrAddToBasket(){
        System.out.println("\nCHOOSE ACTION:\n"+
                "  1. Add product to basket\n" +
                "  Enter: Back\n");
    }

    public static void flashMessage(String msg){
        System.out.println(msg);
    }
}
