package com.prerna.automation.selenium.linkedin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prerna.automation.selenium.linkedin.base.BasePage;
import com.prerna.automation.selenium.linkedin.util.AppendToFile;

import java.util.ArrayList;
import java.util.List;

public class LeadsPage extends BasePage {

	private By leadsTable = By.xpath("//table[contains(@class, 'artdeco-models-table')]//tr");

	public LeadsPage(WebDriver driver) {
		super(driver);
	}

	public void printLeadInfo() {
		List<WebElement> anchors = driver.findElements(By.xpath("//a[contains(@href, '/sales/lists/people/')]"));

		int anchorListSize = anchors.size();

		for (int i = 0; i < anchorListSize; i++) {

			anchors = driver.findElements(By.xpath("//a[contains(@href, '/sales/lists/people/')]"));

			WebElement anchor = anchors.get(i);

			WebElement fileNameElement = anchor.findElement(By.xpath(".//div[1]"));

			String fileName = fileNameElement.getText();

			anchor.click();

			try {
				Thread.sleep(2000);
			} catch (Exception ex) {
			}

			boolean isNextButtonEnabled = true;

			while (isNextButtonEnabled) {

				System.out.println("isNextButtonEnabled: " + isNextButtonEnabled);

				List<WebElement> rows = driver
						.findElements(By.xpath("//tr[contains(@class, 'artdeco-models-table-row')]"));

				for (WebElement row : rows) {
					String rowNum = row.getAttribute("data-x--people-list--row");
					System.out.println("data-x--people-list--row: " + rowNum);

					AppendToFile.appendLineToFile("D:\\Temp\\Files\\" + fileName + ".csv", getRowElementText(row,
							By.xpath(".//span[@class='_lead-detail-entity-details_ocf42k']")) + ","
							+ getRowElementText(row, By.xpath(".//div[@data-anonymize='job-title']"))
							+ ","
							+ getRowElementText(row, By.xpath(".//span[@data-anonymize='company-name']"))
							+ ","
							+ getRowElementText(row, By.xpath(".//td[@data-anonymize='location']")));
				}

				try {
					WebElement button = driver.findElement(By.xpath("//button[@aria-label='Next']"));

					if (button.isEnabled()) {
						button.click();
						try {
							Thread.sleep(2000);
						} catch (Exception ex) {
						}
					} else {
						isNextButtonEnabled = false;
						System.out.println("isNextButtonEnabled: " + isNextButtonEnabled);
					}

				} catch (NoSuchElementException e) {
					isNextButtonEnabled = false;
					System.out.println("isNextButtonEnabled: " + isNextButtonEnabled);
				}

			}

			WebElement backToLeadListsElement = driver.findElement(By.xpath("//a[@href='/sales/lists/people']"));
			backToLeadListsElement.click();

			try {
				Thread.sleep(2000);
			} catch (Exception ex) {
			}

		}

	}

	public String getRowElementText(WebElement row, By by) {
		String result = "";
		try {

			WebElement element = row.findElement(by);
			result = element.getText();

		} catch (NoSuchElementException e) {

		}
		return result;
	}
}
