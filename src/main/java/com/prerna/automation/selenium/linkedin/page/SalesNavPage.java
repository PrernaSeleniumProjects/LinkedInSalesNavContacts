package com.prerna.automation.selenium.linkedin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.prerna.automation.selenium.linkedin.base.BasePage;

public class SalesNavPage extends BasePage {

	// Locator for the Sales Navigator link (already defined in the previous code)
	// nav-small-sales-navigator-icon

	private By salesNavLink = By.xpath("//li-icon[@type='nav-small-sales-navigator-icon']");
	// private By salesNavLink = By.xpath("//span[text()='Leads']");//
	// By.id("mynav-sales");

	// Locator for the Leads menu link (new)
	private By leadsMenuLink = By.xpath("//a[@href='/sales/lists/people']");//By.xpath("//a[text()='Leads']");

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
	
	
	public By getPageCheckElement() {
		return salesNavLink;
	}
}
