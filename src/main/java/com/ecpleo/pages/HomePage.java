package com.ecpleo.pages;

import com.ecpleo.utilities.Browser;
import com.ecpleo.utilities.Helper;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class HomePage {

    Helper helper = Helper.getInstance();

    By amountTextbox = By.id("amount");
    By fromDropDown = By.id("midmarketFromCurrency");
    By toDropDown = By.id("midmarketToCurrency");
    By convertBtn = By.xpath("//button[normalize-space()='Convert']");
    By convertTab = By.xpath("//span[normalize-space()='Convert']");

    By fromText = By.xpath("//p[contains(@class,'result__ConvertedText')]");
    By toText = By.xpath("//p[contains(@class,'result__BigRate')]");

    public void clickConvertTab() {
        System.out.println("Clicking convert tab");
        helper.clickObject(convertTab);
    }

    public void setAmountTextbox(int amount) {
        System.out.println("Entering amount in text box " + amount);
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        helper.sendInput(amountTextbox, String.valueOf(amount));
    }

    public void setFromDropDown(String currency) {
        System.out.println("Setting " + currency + " in from dropdown");
        helper.clickObject(fromDropDown);
        helper.sendInput(fromDropDown, currency);
        helper.sendInput(fromDropDown, Keys.RETURN);
    }

    public void setToDropDown(String currency) {
        System.out.println("Seeting " + currency + " in to dropdown");
        helper.clickObject(toDropDown);
        helper.sendInput(toDropDown, currency);
        helper.sendInput(toDropDown, Keys.RETURN);
    }

    public void clickConvertBtn() {
        System.out.println("Clicking convert button");
        helper.clickObject(convertBtn);
    }

    public void setConvertCurrencyField(int amount, String fromCurrency, String toCurrency) {
        setAmountTextbox(amount);
        setFromDropDown(fromCurrency);
        setToDropDown(toCurrency);
        clickConvertBtn();
    }

    public String getFromText() {
        return helper.getText(fromText);
    }

    public String getToText() {
        return helper.getText(toText);
    }


}