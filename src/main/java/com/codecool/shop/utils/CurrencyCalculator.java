package com.codecool.shop.utils;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class CurrencyCalculator {
    private String baseURL = "http://api.fixer.io/latest?base=";
    private Currency baseCurrency;
    private HashMap <Currency, Float> rates;

    public CurrencyCalculator(Currency chosenCurrency){
        setCurrencyData(chosenCurrency);
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public float calculatePrice(Currency defaultCurrency, Float price){
        if (defaultCurrency.equals(baseCurrency)){
            return price;
        }
        Float rate = rates.get(defaultCurrency);
        if (rate > 1) {
            return price / rate;
        } else{
            return price*rate;
        }
    }

    public void setCurrencyData(Currency chosenCurrency){
        baseCurrency = chosenCurrency;
        setRates();
    }

    private void setRates() {
        try {
            rates = new HashMap<>();
            JSONParser parser = new JSONParser();
            URL oracle = new URL(baseURL + baseCurrency);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = in.readLine();
            JSONObject jsonObject = (JSONObject) parser.parse(inputLine);
            JSONObject rts = (JSONObject) jsonObject.get("rates");
            Set<String> keys = rts.keySet();
            Iterator<String> i = keys.iterator();
            while (i.hasNext()) {
                String key = i.next();
                Double doubleRate = (Double) rts.get(key);
                float rate = doubleRate.floatValue();
                rates.put(Currency.getInstance(key), rate);
            }
            in.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
