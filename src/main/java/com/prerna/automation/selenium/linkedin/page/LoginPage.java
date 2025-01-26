package com.prerna.automation.selenium.linkedin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.prerna.automation.selenium.linkedin.base.BasePage;

public class LoginPage extends BasePage {

	private By usernameField = By.id("username");
	private By passwordField = By.id("password");
	private By loginButton = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void open() {
		driver.get("https://www.linkedin.com/login");
	}

	public void login(String username, String password) {
		open();
		sendKeysToElement(usernameField, username);
		sendKeysToElement(passwordField, password);
		clickElement(loginButton);
	}
}
