package com.prerna.automation.selenium.linkedin.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prerna.automation.selenium.linkedin.base.BasePage;

import java.util.List;

public class LeadsPage extends BasePage {

    private By leadsTable = By.xpath("//table[contains(@class, 'leads-list')]//tr");

    public LeadsPage(WebDriver driver) {
        super(driver);
    }

    public void printLeadInfo() {
        List<WebElement> leadsList = driver.findElements(leadsTable);
        for (WebElement leadRow : leadsList) {
            try {
                String name = leadRow.findElement(By.xpath(".//td[@class='name']")).getText();
                String account = leadRow.findElement(By.xpath(".//td[@class='account']")).getText();
                String geography = leadRow.findElement(By.xpath(".//td[@class='geography']")).getText();

                System.out.println("Lead Info:");
                System.out.println("Name: " + name);
                System.out.println("Account: " + account);
                System.out.println("Geography: " + geography);
            } catch (Exception e) {
                // Skip rows that don't contain the necessary info
                continue;
            }
        }
    }
}
