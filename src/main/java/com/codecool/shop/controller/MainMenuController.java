package com.codecool.shop.controller;
import com.codecool.shop.model.*;
import com.codecool.shop.views.*;
import java.util.Currency;

public class MainMenuController extends BaseMenuController {
    public void runMenu() {
        Boolean quitProgram = false;
        Integer userChoice;
        MenuView.displayWelcome();
        getUserCurrency();
        while (!quitProgram) {
            MenuView.displayMain();
            userChoice = getUserChoice();
            switch (userChoice) {
                case 1:
                    new ProductMenuController().runMenu();
                    break;
                case 2:
                    new CartMenuController().runMenu();
                    break;
                case 3:
                    new SearchMenuController().runMenu();
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
}