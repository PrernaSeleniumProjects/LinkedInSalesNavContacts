package com.prerna.automation.selenium.linkedin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.prerna.automation.selenium.linkedin.base.BasePage;

public class SalesNavPage extends BasePage {

    // Locator for the Sales Navigator link (already defined in the previous code)
    private By salesNavLink = By.id("mynav-sales");

    // Locator for the Leads menu link (new)
    private By leadsMenuLink = By.xpath("//span[text()='Leads']");

    public SalesNavPage(WebDriver driver) {
        super(driver);
    }

    // Method to navigate to Sales Navigator
    public void goToSalesNavigator() {
        clickElement(salesNavLink);
    }

    // Method to navigate to Leads page
    public void goToLeadsPage() {
        clickElement(leadsMenuLink);
    }
}

