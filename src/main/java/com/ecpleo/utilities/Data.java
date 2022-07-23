package com.ecpleo.utilities;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "conversion-value")
    public Object[][] dpMethod() {
        return new Object[][]{
                {10, "EUR", "GBP"},
                {20, "EUR", "GBP"},
                {30, "EUR", "GBP"},
                {40, "EUR", "GBP"},
                {50, "EUR", "GBP"}
        };
    }
}
