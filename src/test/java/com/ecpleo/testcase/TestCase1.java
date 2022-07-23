package com.ecpleo.testcase;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecpleo.pages.HomePage;
import com.ecpleo.utilities.BaseClass;
import com.ecpleo.utilities.Data;
import com.ecpleo.utilities.ExtentManager;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestCase1 extends BaseClass {

    HomePage homePage;
    SoftAssert softAssert;

    @Test(dataProvider = "conversion-value", dataProviderClass = Data.class)
    public void verifyConversion(int amount, String fromCurrency, String toCurrency) {
        homePage = new HomePage();

        ExtentTest test = ExtentManager.extent.createTest("MyFirstTest");
        test.pass("Text details");
        test.log(Status.PASS, "Text details");

        homePage.clickConvertTab();
        homePage.setConvertCurrencyField(amount, fromCurrency, toCurrency);

        softAssert = new SoftAssert();

        softAssert.assertEquals(homePage.getFromText(), amount + ".00 Euros =", "");
        softAssert.assertTrue(homePage.getToText().contains("British Pounds"), "");

        softAssert.assertAll();

    }


}
