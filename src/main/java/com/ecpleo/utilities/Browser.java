package com.ecpleo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Browser {

    private static Browser browser = null;

    public static Browser getInstance() {
        if (browser == null)
            browser = new Browser();
        return browser;
    }

    static WebDriver driver;

    public void initializeBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(Helper.getInstance().readPropertyFile("baseurl"));

    }

    public void killDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
