package com.qa;

import com.qa.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
// Assuming ConfigReader is available
// Assuming TestNG dependencies are in build.gradle.kts

public abstract class BaseTest { // Use 'abstract' to prevent accidental instantiation

    // Protected fields can be accessed by all classes extending BaseTest
    protected TA_Driver taDriver;
    protected WebDriver driver;
    protected  TA_Wait ta_wait;
    protected  TA_Actions ta_actions;
    protected  LandingPage landingPage;
    protected  CareerPage careerPage;

    // Key used to retrieve the URL from the configuration file
    private final String BASE_URL_KEY = "baseURL";

    /**
     * Initialization method run before every Test method.
     */
    @BeforeMethod
    public void setup() {
        // 1. Read URL from Configuration
        String url = ConfigReader.getProperty(BASE_URL_KEY);

        // 2. Initialize Driver
        this.taDriver = new TA_Driver();
        this.driver = taDriver.driverInit("chrome");

        // 3. Navigate
        taDriver.goToURL(url);
        landingPage=new LandingPage(this.driver);
        careerPage=new CareerPage(this.driver);
        this.driver.manage().window().maximize();
    }

    /**
     * Cleanup method run after every Test method.
     */
    @AfterMethod
    public void teardown() {
        if (taDriver != null) {
            taDriver.driverQuit();
        }
    }


}