package com.ecpleo.utilities;

import com.ecpleo.module.PopUp;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
    static String env;

    @BeforeClass
    @Parameters({"browser", "environment"})
    public void setUp(String browser, String environment) {
        env = environment;
        Browser.getInstance().initializeBrowser(browser);
        new PopUp().closeGetMore();
    }

    @AfterClass
    public void tearDown(){
        Browser.getInstance().killDriver();
    }

}
