package com.prerna.automation.selenium.linkedin.test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prerna.automation.selenium.linkedin.page.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkedInTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private SalesNavPage salesNavPage;
	private NotificationPage notificationPage;
	private LeadsPage leadsPage;

	public LinkedInTest() {
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
		options.addArguments("Accept-Language=en-US,en;q=0.9");
		options.addArguments("Accept-Encoding=gzip, deflate, br");

		options.addArguments("--start-maximized"); // Open Chrome in maximized window
		options.addArguments("--disable-infobars"); // Disable "Chrome is being controlled by automated test software"
		// options.addArguments("--headless"); // If you need headless mode (optional)
		options.addArguments("--disable-gpu"); // Disable GPU (optional)
		options.addArguments("--no-sandbox"); // Bypass OS security model (useful for CI/CD)
		options.addArguments("window-size=1920x1080"); // Set window size
		options.addArguments("--remote-allow-origins=*");

		// Initialize WebDriver
		// WebDriver driver = new ChromeDriver(options);

		driver = new ChromeDriver(options);
		loginPage = new LoginPage(driver);
		salesNavPage = new SalesNavPage(driver);
		notificationPage = new NotificationPage(driver);
		leadsPage = new LeadsPage(driver);
	}

	public void runTest(String username, String password) {
		try {

			// Step 1: Login
			loginPage.login(username, password);

			// Step 2: Navigate to Sales Navigator

			System.out.println("Step: loggged in");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(salesNavPage.getPageCheckElement()));

			String mainWindowHandle = driver.getWindowHandle();
			salesNavPage.goToSalesNavigator();

			System.out.println("Step: sales navigator");

			try {
				wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOfElementLocated(notificationPage.getPageCheckElement()));
			} catch (Exception ex) {

			}

			Set<String> windowHandles = driver.getWindowHandles();

			// Switch to the new tab
			for (String handle : windowHandles) {
				if (!handle.equals(mainWindowHandle)) {
					driver.switchTo().window(handle); // Switch to new tab
					break;
				}
			}

			System.out.println("Step: closing notification");

			// Step 3: Close any notifications
			notificationPage.closeNotificationBar();

			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(salesNavPage.getLeadsPageCheckElement()));

			System.out.println("Step: going to leads");

			// Step 4: Go to Leads page
			salesNavPage.goToLeadsPage();

			try {
				Thread.sleep(5000);
			} catch (Exception ex) {

			}

			System.out.println("Step: leads opened");

			// Step 5: Print lead information
			leadsPage.printLeadInfo();

			System.out.println("Step: done................");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {

		LinkedInTest linkedInTest = new LinkedInTest();

		// Run the test with your credentials
		linkedInTest.runTest("sourav@pipra.solutions", "Sour@v91097");

		// Close the browser after the test
		// linkedInTest.closeBrowser();
	}
}
