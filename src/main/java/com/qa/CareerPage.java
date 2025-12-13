package com.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class CareerPage {

        TA_Actions ta_actions;
        TA_Wait ta_wait;
        WebDriver driver;

    public CareerPage(WebDriver driver) {
        this.driver=driver;
        this.ta_actions=new TA_Actions(driver);
        this.ta_wait=new TA_Wait();
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(xpath = "//p[text()='Search and apply for one of our open positions or internships.']/following-sibling::p//a")
    WebElement seeAllOpenLink;

    @FindBy(id = "keyword")
    WebElement searchBoxField;

    @FindBy(xpath = "//span[text()='Search']")
    WebElement searchBoxBtn;

    public void clickOnSeeAllOpenLink(){
        ta_wait.waitInbuilt(this.driver,"clickable",seeAllOpenLink);
        ta_actions.elementToBeClick(seeAllOpenLink);
    }

    public void switchToCurrentOpeningPage(){
        String originalWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Check if a new window was actually opened
        if (allWindowHandles.size() <= 1) {
            throw new RuntimeException("New tab or window did not open after clicking the link.");
        }

        // 3. Loop through all handles and switch to the new one
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("INFO: Switched to new tab/window handle: " + windowHandle);
                return; // Exit after successful switch
            }
        }
        // This line should technically never be reached if a new tab opened
        System.err.println("WARNING: Could not find the new window handle.");
    }

    public void enterAndSeacrh(String title){
        ta_actions.sendKeys(searchBoxField,title);
        ta_actions.elementToBeClick(searchBoxBtn);
    }
}
