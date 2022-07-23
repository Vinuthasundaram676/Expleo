package com.ecpleo.module;

import com.ecpleo.utilities.Helper;
import org.openqa.selenium.By;

public class PopUp {

    Helper helper = Helper.getInstance();

    By getMoreCloseButton = By.xpath("//button[@role='button' and contains(@id,'yie-close-button')]");
    By cookieButton = By.xpath("//*[@id=\"__next\"]/div[3]/section/div[2]/button[2]");
    public void closeGetMore(){
        helper.waitForBrowserToLoadCompletely();
        helper.clickObject(cookieButton);
        helper.clickObject(getMoreCloseButton);
    }

}
