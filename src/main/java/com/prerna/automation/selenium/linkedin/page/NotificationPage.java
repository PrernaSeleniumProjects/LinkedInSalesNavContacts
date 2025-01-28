package com.prerna.automation.selenium.linkedin.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prerna.automation.selenium.linkedin.base.BasePage;

public class NotificationPage extends BasePage {

    private By notificationDismissButton = By.id("ember5"); //By.xpath("//button[@aria-label='Dismiss']");
    
    private By notificationPanel = By.xpath("//div[@data-sn-view-name='subpage-notifications-panel']"); //By.xpath("//button[@aria-label='Dismiss']");
   

    public NotificationPage(WebDriver driver) {
        super(driver);
    }

    public void closeNotificationBar() {
        try {
        	WebElement notificationPanelDiv = driver.findElement(notificationPanel);
        	
        	if (isElementExists(notificationPanelDiv, By.xpath("(.//button)[1]"))) {
				WebElement closeButton = notificationPanelDiv.findElement(By.xpath("(.//button)[1]"));
				clickWebElement(closeButton);
    		}
        } catch (Exception e) {
            System.out.println("No notification bar found or there is exception.");
        }
    }
    
    public By getPageCheckElement() {
		return notificationPanel;
	}
}
