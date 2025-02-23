package com.prerna.automation.selenium.linkedin.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prerna.automation.selenium.linkedin.util.GeneralUtil;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Wait for an element to be visible
	public WebElement waitForElementVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Wait for an element to be clickable
	public WebElement waitForElementClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Click on an element
	public void clickElement(By locator) {
		WebElement element = waitForElementClickable(locator);
		element.click();
	}

	// Send keys to an input field
	public void sendKeysToElement(By locator, String keys) {
		WebElement element = waitForElementVisible(locator);
		element.sendKeys(keys);
	}

	// Get text from an element
	public String getElementText(By locator) {
		WebElement element = waitForElementVisible(locator);
		return element.getText();
	}

	public boolean isElementExists(WebElement parentElement, By locator) {
		try {
			parentElement.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getElementTextIfExists(WebElement parentElement, By locator) {
		try {
			WebElement element = parentElement.findElement(locator);
			return element.getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void clickWebElement(WebElement webElement) {
		try {
			Thread.sleep(GeneralUtil.generateRandom(1000, 5000));
		} catch (Exception ex) {
		}
		webElement.click();
	}
}