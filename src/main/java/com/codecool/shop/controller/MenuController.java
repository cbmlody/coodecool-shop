package com.codecool.shop.controller;

import java.util.Scanner;

public class MenuController {
    private static final MenuController INSTANCE = new MenuController();
    private MenuController() {}
    private static Scanner read = new Scanner(System.in);
    private static Boolean quitProgram = true;
    private static Boolean quitProductMenu = true;
    private static Boolean quitCartMenu = true;
    private static Boolean quitSearchMenu = true;

    private static MenuController getInstance() {
        return INSTANCE;
    }g
}