package com.codecool.shop.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileReader {

    public String getStringFromFile(String pathToResource) {
        String string;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            File file = new File(this.getClass().getResource( pathToResource ).toURI());
            java.io.FileReader fileReader = new java.io.FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string);
            }
            bufferedReader.close();
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println("THere is no file to read.");
        }
        return stringBuilder.toString();
    }
}
