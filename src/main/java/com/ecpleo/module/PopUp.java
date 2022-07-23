package com.ecpleo.module;

import com.ecpleo.utilities.Helper;
import org.openqa.selenium.By;

public class PopUp {

    Helper helper = Helper.getInstance();

    By getMoreCloseButton = By.xpath("//button[@role='button' and contains(@id,'yie-close-button')]");

    public void closeGetMore(){
        helper.waitForBrowserToLoadCompletely();
        helper.clickObject(getMoreCloseButton);
    }

}
