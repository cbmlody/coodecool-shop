package com.codecool.shop.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mercutio on 24.05.17.
 */
class FileReaderTest {

    FileReader testReader;

    @BeforeEach
    void setUp() {
        testReader = new FileReader();
    }

    @Test
    void testIfGetStringFromFileThrowsException() {
        assertThrows(IOException.class, () ->
        testReader.getStringFromFile("/iuhqgabfiseuabf.txt"));
    }


}