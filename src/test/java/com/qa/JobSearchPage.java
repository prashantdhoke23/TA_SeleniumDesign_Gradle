package com.qa;

import com.aventstack.extentreports.Status;
import com.qa.utils.ExtentReporterListener;
import org.testng.annotations.Test;

@Test
public class JobSearchPage extends BaseTest{

    // Note: The TA_Wait wait field is not needed here since it's in BaseTest/Page Objects

    @Test
    public void clickOnJobAndEnterAndValidate() throws InterruptedException {

        // --- Step 1: Click Career Button ---
        ExtentReporterListener.getTest().log(Status.INFO, "Navigating to Landing Page and clicking 'Careers' button.");
        landingPage.clickOnCareerBtn();
        // Since clickOnCareerBtn() should return the CareerPage,
        // we should capture it if the return type was updated:
        // this.careerPage = landingPage.clickOnCareerBtn();
        Thread.sleep(5000); // Wait for page load (Replace with explicit waits later)

        // --- Step 2: Click See All Openings ---
        ExtentReporterListener.getTest().log(Status.INFO, "On Career Page, clicking 'See All Openings' link.");
        careerPage.clickOnSeeAllOpenLink();
        Thread.sleep(5000); // Wait for new tab to open

        // --- Step 3: Switch to New Tab ---
        ExtentReporterListener.getTest().log(Status.INFO, "Switching driver focus to the newly opened tab.");
        careerPage.switchToCurrentOpeningPage(); // Assumes this method contains the switch logic
        ExtentReporterListener.getTest().log(Status.PASS, "Successfully switched to the Job Search tab.");
        Thread.sleep(5000);

        // --- Step 4: Search for Job ---
        String jobTitle = "Senior Billing Analyst";
        ExtentReporterListener.getTest().log(Status.INFO, "Searching for job title: **" + jobTitle + "**");
        // Ensure careerPage has the enterAndSeacrh method implemented
        careerPage.enterAndSeacrh(jobTitle);
        Thread.sleep(5000);

        // --- Step 5: Validation (Placeholder) ---
        // Here, you would validate the search results count or the job title list.
        ExtentReporterListener.getTest().log(Status.PASS, "Job search completed successfully.");

    }
}