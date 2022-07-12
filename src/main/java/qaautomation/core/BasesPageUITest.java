package qaautomation.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import qaautomation.pageobjects.ClearTripSuccessLoginPage;
import qaautomation.pageobjects.CleartripFlightPage;
import qaautomation.pageobjects.CleartripHomePage;
import qaautomation.utils.SetupUtilities;

@SuppressWarnings("static-access")
public class BasesPageUITest implements IReporter {

	public WebDriver uidriver;
	public BasePage basePage;
	public static Reporter reporter;
	public Logger log;
	public WebDriverWait explicitWait;
	public static final String USER_EMAIL = "UserEmail";
	public static final String USER_PASS = "UserPass";

	public CleartripHomePage cleartripHomePage;
	public CleartripFlightPage cleartripFlightPage;
	public ClearTripSuccessLoginPage clearTripSuccessLoginPage;

	@Parameters({ "browser" })
	@BeforeSuite()
	public void beforeSuite(String browser) {

		Reporter.log("In Before Suite");
		System.out.println(browser);

		if (browser.toLowerCase().equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			uidriver = new ChromeDriver(chromeOptions);
			chromeOptions.addArguments("disable-infobars");

			chromeOptions.addArguments("--profile-directory=Default");
			chromeOptions.addArguments("--whitelisted-ips");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disable-plugins-discovery");
			chromeOptions.addArguments("--incognito");
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			chromeOptions.setExperimentalOption("useAutomationExtension", false);

			Reporter.log("Chrome Browser Invoked");
			System.out.println("Chrome Browser Invoked");
		} else if (browser.toLowerCase().equalsIgnoreCase("firefox")) {
			FirefoxOptions firefoxOptns = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			uidriver = new FirefoxDriver(firefoxOptns);
			firefoxOptns.addArguments("disable-infobars");
			Reporter.log("Firefox Browser Invoked");
			System.out.println("Invalid Browser Provided");
		} else {
			System.out.println("Invalid Browser Provided");
			Reporter.log("Invalid Browser Provided");
		}

		if (uidriver == null) {
			log.info("Driver did not initialize correctly");
		}

		uidriver.manage().window().maximize();
		basePage = new BasePage(uidriver) {
		};
		uidriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

	}

	@AfterSuite()
	public void afterSuite() {
		uidriver.close();
		uidriver = null;
	}

	public static void waitSeconds(int i) throws InterruptedException {
		Thread.sleep(1000 * i);
	}

	@BeforeClass()
	public void loadClassProperties() throws IOException {

		//uidriver.get(SetupUtilities.getPropertyValue("URL"));
	}

}
