package com.prerna.automation.selenium.linkedin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prerna.automation.selenium.linkedin.base.BasePage;
import com.prerna.automation.selenium.linkedin.bean.Person;
import com.prerna.automation.selenium.linkedin.util.AppendToFile;
import com.prerna.automation.selenium.linkedin.util.Excel;
import com.prerna.automation.selenium.linkedin.util.GeneralUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LeadsPage extends BasePage {

	private By leadsTable = By.xpath("//a[contains(@href, '/sales/lists/people/')]");

	public LeadsPage(WebDriver driver) {
		super(driver);
	}

	public By getPageCheckElement() {
		return leadsTable;
	}

	public void printLeadInfo() {
		List<WebElement> anchors = driver.findElements(leadsTable);

		int anchorListSize = anchors.size();
		
		Excel excel = new Excel();

		for (int i = 0; i < anchorListSize; i++) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/sales/lists/people/')]")));

			anchors = driver.findElements(By.xpath("//a[contains(@href, '/sales/lists/people/')]"));

			WebElement anchor = anchors.get(i);

			WebElement fileNameElement = anchor.findElement(By.xpath(".//div[1]"));

			String fileName = fileNameElement.getText();
			
			excel.createSheet(fileName.substring(0, 28));

			AppendToFile.appendLineToFile("D:\\Temp\\Files\\" + fileName + ".csv",
					"Name,Job Title,Company Name,Location,Phone,Website,Social,Emails");

			clickWebElement(anchor);

			boolean isNextButtonEnabled = true;
			ArrayList<Person> personList = new ArrayList();

			while (isNextButtonEnabled) {

				// System.out.println("isNextButtonEnabled: " + isNextButtonEnabled);

				wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//tr[contains(@class, 'artdeco-models-table-row')]")));

				List<WebElement> rows = driver
						.findElements(By.xpath("//tr[contains(@class, 'artdeco-models-table-row')]"));
				
				

				for (WebElement row : rows) {
					// String rowNum = row.getAttribute("data-x--people-list--row");
					// System.out.println("data-x--people-list--row: " + rowNum);

					WebElement aNameElement = row.findElement(By.xpath(".//a[@data-anonymize='person-name']"));

					Person person = new Person();
					personList.add(person);
					
					person.setPersonName(getElementTextIfExists(row,
							By.xpath(".//span[@class='_lead-detail-entity-details_ocf42k']")));
					person.setJobTitle(getElementTextIfExists(row, By.xpath(".//div[@data-anonymize='job-title']")));
					person.setCompanyName(
							getElementTextIfExists(row, By.xpath(".//span[@data-anonymize='company-name']")));
					person.setLocation(getElementTextIfExists(row, By.xpath(".//td[@data-anonymize='location']")));

					clickWebElement(aNameElement);

					wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//section[@data-sn-view-name='lead-contact-info']")));

					WebElement contactInfo = driver
							.findElement(By.xpath("//section[@data-sn-view-name='lead-contact-info']"));

					if (isElementExists(contactInfo, By.xpath(".//address[contains(@class,'panel')]"))) {
						WebElement addressInfo = contactInfo
								.findElement(By.xpath(".//address[contains(@class,'panel')]"));

						// System.out.println(addressInfo.getAttribute("innerHTML"));

						if (isElementExists(addressInfo, By.xpath(".//li-icon[@type='mobile-icon']"))) {
							WebElement phoneElement = addressInfo
									.findElement(By.xpath(".//span[@data-anonymize='phone']"));
							person.addPhoneNumber(phoneElement.getText());
							checkAndClickShowAll(addressInfo, person);
						} else if (isElementExists(addressInfo, By.xpath(".//li-icon[@type='phone-handset-icon']"))) {
							WebElement handsetPhoneNumberElement = addressInfo
									.findElement(By.xpath(".//span[@data-anonymize='phone']"));
							person.addHandsetPhoneNumber(handsetPhoneNumberElement.getText());
							checkAndClickShowAll(addressInfo, person);
						} else if (isElementExists(addressInfo, By.xpath(".//li-icon[@type='twitter-icon']"))) {
							WebElement twitterElement = addressInfo
									.findElement(By.xpath(".//span[@data-anonymize='remove']"));
							person.addTwitter(twitterElement.getText());
							checkAndClickShowAll(addressInfo, person);
						}else if (isElementExists(addressInfo, By.xpath(".//li-icon[@type='link-icon']"))) {
							WebElement linkElement = addressInfo
									.findElement(By.xpath(".//span[@data-anonymize='remove']"));
							person.addLink(linkElement.getText());
							checkAndClickShowAll(addressInfo, person);
						} else if (isElementExists(addressInfo, By.xpath(".//li-icon[@type='envelope-icon']"))) {
							WebElement emailElement = addressInfo
									.findElement(By.xpath(".//span[@data-anonymize='email']"));
							person.addEmail(emailElement.getText());
							checkAndClickShowAll(addressInfo, person);
						} else {
							System.out.println("nothing found :(");
							String innerHTML = addressInfo.getAttribute("innerHTML");
							AppendToFile.appendLineToFile("D:\\Temp\\Files\\" + "contactinfo" + ".txt", innerHTML);
							AppendToFile.appendLineToFile("D:\\Temp\\Files\\" + "contactinfo" + ".txt",
									"---------------------------------");
						}

					} else {
						System.out.println("section lead-contact-info doesn't exists");
					}

					AppendToFile.appendLineToFile("D:\\Temp\\Files\\" + fileName + ".csv", person.csvString());
					// System.exit(0);
				}

				try {
					WebElement button = driver.findElement(By.xpath("//button[@aria-label='Next']"));

					if (button.isEnabled()) {
						clickWebElement(button);
					} else {
						isNextButtonEnabled = false;
						// System.out.println("isNextButtonEnabled: " + isNextButtonEnabled);
					}

				} catch (NoSuchElementException e) {
					isNextButtonEnabled = false;
					// System.out.println("isNextButtonEnabled: " + isNextButtonEnabled);
				}

			}
			
			excel.createSheetData(personList);
			

			WebElement backToLeadListsElement = driver.findElement(By.xpath("//a[@href='/sales/lists/people']"));
			clickWebElement(backToLeadListsElement);
		}
		
		excel.saveExcel(GeneralUtil.generateFileName());

	}

	public void checkAndClickShowAll(WebElement parent, Person person) {

	//	System.out.println(parent.getAttribute("innerHTML"));

		if (isElementExists(parent, By.xpath(".//button[@type='button']"))) {

			WebElement buttonEle = parent.findElement(By.xpath(".//button[@type='button']"));

			if (isElementExists(buttonEle, By.xpath("(.//span)[1]"))) {
				WebElement spanShowAll = buttonEle.findElement(By.xpath("(.//span)[1]"));

				if (spanShowAll.getText().contains("Show all")) {
					clickWebElement(spanShowAll);

					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[contains(@class,'artdeco-modal__header')]")));

					getDetailsFromContactPopup(person);

					WebElement closeModelDialogButton = driver
							.findElement(By.xpath("//button[@data-test-modal-close-btn='']"));

					clickWebElement(closeModelDialogButton);
				} else {
					System.out.println("No show all exists in address");
				}
			} else {
				System.out.println("No button exists in address");
			}

		} else {
			System.out.println("No button exists in address");
		}
	}

	public void getDetailsFromContactPopup(Person person) {

		List<WebElement> sections = driver
				.findElements(By.xpath("//section[contains(@class,'contact-info-form__phone')]"));

		if (sections.size() > 0) {
			for (WebElement section : sections) {
				person.addPhoneNumber(getElementTextIfExists(section, By.xpath(".//a[@data-anonymize='phone']")));
			}
		} else {
			System.out.println("contact-info-form__phone  sections.size() = 0");
		}

		sections = driver.findElements(By.xpath("//section[contains(@class,'contact-info-form__email')]"));

		if (sections.size() > 0) {
			for (WebElement section : sections) {
				person.addEmail(getElementTextIfExists(section, By.xpath(".//a[@data-anonymize='email']")));
			}
		} else {
			System.out.println("contact-info-form__email  sections.size() = 0");
		}

		sections = driver.findElements(By.xpath("//section[contains(@class,'contact-info-form__website')]"));

		if (sections.size() > 0) {
			for (WebElement section : sections) {
				person.addLink(getElementTextIfExists(section, By.xpath(".//a[@data-anonymize='url']")));
			}
		} else {
			System.out.println("contact-info-form__website  sections.size() = 0");
		}

		sections = driver.findElements(By.xpath("//section[contains(@class,'contact-info-form__social')]"));

		if (sections.size() > 0) {
			for (WebElement section : sections) {
				person.addTwitter(getElementTextIfExists(section, By.xpath(".//span[@data-anonymize='person-name']")));
			}
		} else {
			System.out.println("contact-info-form__social sections.size() = 0");
		}
	}
}
