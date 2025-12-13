package com.qa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    TA_Actions ac;
    TA_Wait ta_wait;
    private WebDriver driver;


    public LandingPage(WebDriver driver) {
        this.driver=driver;
        this.ac = new TA_Actions(driver);
        this.ta_wait=new TA_Wait();

        PageFactory.initElements(driver,this);


    }


    @FindBy(xpath = "//h1[text()='Provider resources']")
     WebElement prodiverResourceText;

    @FindBy(xpath = "//a[contains(text(),'Careers')]")
    WebElement careerBtmn;

    public String getrodiverResourceText(){
        return ac.getText(prodiverResourceText);
    }

    public void clickOnCareerBtn(){
        // Wait for the element to be visible/clickable (still needed for robustness)
        ta_wait.waitInbuilt(this.driver, "clickable", careerBtmn);

        // This single line now runs the robust try-catch logic defined above
        ac.elementToBeClick(careerBtmn);
    }


}
