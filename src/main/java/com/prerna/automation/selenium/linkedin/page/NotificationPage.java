package com.prerna.automation.selenium.linkedin.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prerna.automation.selenium.linkedin.base.BasePage;

public class NotificationPage extends BasePage {

    private By notificationDismissButton = By.id("ember5"); //By.xpath("//button[@aria-label='Dismiss']");

    public NotificationPage(WebDriver driver) {
        super(driver);
    }

    public void closeNotificationBar() {
        try {
            WebElement dismissButton = waitForElementVisible(notificationDismissButton);
            dismissButton.click();
        } catch (Exception e) {
            System.out.println("No notification bar found.");
        }
    }
}
