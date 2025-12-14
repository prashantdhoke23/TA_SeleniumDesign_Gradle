package com.qa;

import org.testng.annotations.Test;


//Ignore
public class ProverProtal extends BaseTest {

    @Test
    public void providerPortalTest() {
        // driver is initialized and navigated by BaseTest.setup()

        // 1. Instantiate Page Object
        // Uses the 'driver' inherited from BaseTest
        LandingPage landingPage = new LandingPage(driver);

        // 2. Execute the Page Action+
        String textResource = landingPage.getrodiverResourceText();

        // 3. Assert/Report
        System.out.println("--- Test Execution Result ---");
        System.out.println("Text Resource Found: " + textResource);
    }


}