package com.ecpleo.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Helper {

    private static Helper helper = null;

    public static Helper getInstance() {
        if (helper == null)
            helper = new Helper();
        return helper;
    }


    final static Logger Log = Logger.getLogger(Helper.class);

    public void clickObject(By loc) {
        WebElement element = Browser.driver.findElement(loc);
        try {
            verifyElementExists(element);
            element.click();
            Log.info("Pass | Element -> '" + element + "' is clicked in APP Page");
        } catch (Exception e) {
            Log.info("Fail | Element -> '" + element + "' is not present in the APP Page");

        }
    }

    public String getText(By loc) {
        WebElement element = Browser.driver.findElement(loc);
        try {
            verifyElementExists(element);
            return element.getText();
        } catch (Exception e) {

        }
        return null;
    }

    public boolean verifyElementExists(WebElement element) {
        String exception = "";
        int counter = 0;
        boolean isElementFound = false;

        do {
            try {
                Thread.sleep(1000);
                isElementFound = element.isDisplayed();
                if (isElementFound)
                    break;
            } catch (Exception e) {
                counter++;
                exception = e.getCause().toString();
            }
        } while (counter < 1);

        Log.info(isElementFound ? "Pass | Element '" + element + "' Existed" :
                "Fail | Element '" + element + "' not Existed - Exception : \n" + exception);
        return isElementFound;
    }


    public void sendInput(By loc, String input) {
        WebElement element = Browser.driver.findElement(loc);

        try {
            verifyElementExists(element);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys("");
            element.clear();
            element.sendKeys(input);
            Log.info("Pass | Element -> '" + element + "' is present in APP Page, Entered Value -> " + input);
        } catch (Exception e) {
            Log.info("Fail | Element -> '" + element + "' is not present in the APP Page to enter Text");
        }
    }

    public void sendInput(By loc, Keys input) {
        WebElement element = Browser.driver.findElement(loc);

        try {
            element.sendKeys(input);
            Log.info("Pass | Element -> '" + element + "' is present in APP Page, Entered Value -> " + input);
        } catch (Exception e) {
            Log.info("Fail | Element -> '" + element + "' is not present in the APP Page to enter Text");
        }
    }

    public String readPropertyFile(String key) {
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/resources/config_" + BaseClass.env + ".properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            Log.info(key + " --> " + prop.getProperty(key));

            return prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public void waitForBrowserToLoadCompletely() {
        String state = null;
        String oldstate = null;
        try {
            System.out.print("Waiting for browser loading to complete");
            int i = 0;
            while (i < 5) {
                Thread.sleep(1000);
                state = ((JavascriptExecutor) Browser.driver).executeScript("return document.readyState;").toString();
                System.out.print("." + Character.toUpperCase(state.charAt(0)) + ".");
                if (state.equals("interactive") || state.equals("loading"))
                    break;
                /*
                 * If browser in 'complete' state since last X seconds. Return.
                 */

                if (i == 1 && state.equals("complete")) {
                    System.out.println();
                    return;
                }
                i++;
            }
            i = 0;
            oldstate = null;
            Thread.sleep(2000);

            /*
             * Now wait for state to become complete
             */
            while (true) {
                state = ((JavascriptExecutor) Browser.driver).executeScript("return document.readyState;").toString();
                System.out.print("." + state.charAt(0) + ".");
                if (state.equals("complete"))
                    break;

                if (state.equals(oldstate))
                    i++;
                else
                    i = 0;
                /*
                 * If browser state is same (loading/interactive) since last 60
                 * secs. Refresh the page.
                 */
                if (i == 15 && state.equals("loading")) {
                    System.out.println("\nBrowser in " + state + " state since last 60 secs. So refreshing browser.");
                    Browser.driver.navigate().refresh();
                    System.out.print("Waiting for browser loading to complete");
                    i = 0;
                } else if (i == 6 && state.equals("interactive")) {
                    System.out.println(
                            "\nBrowser in " + state + " state since last 30 secs. So starting with execution.");
                    return;
                }

                Thread.sleep(4000);
                oldstate = state;

            }
            System.out.println();

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}