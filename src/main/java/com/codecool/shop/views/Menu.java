package com.codecool.shop.views;

public final class Menu {
    private static final Menu MENU = new Menu();
    private Menu() {}
    public static Menu getInstance() {
        return MENU;
    }
}
