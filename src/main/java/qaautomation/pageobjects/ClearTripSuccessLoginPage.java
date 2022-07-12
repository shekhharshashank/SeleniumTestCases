package qaautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qaautomation.core.BasePage;

public class ClearTripSuccessLoginPage extends BasePage {

	public ClearTripSuccessLoginPage(WebDriver uidriver) {
		super(uidriver);
		createExplicitWait(loggedIntoolTip, 10);
	}

	private By yourTripsBtn = By.cssSelector("div[class='p-relative'] button");
	private By loggedInEmail=By.cssSelector("li.ls-reset div");
	private By loggedIntoolTip=By.cssSelector("p[class^='to-ellipsis']");


	public void clickyourTripsBtn() throws InterruptedException {
		waitSeconds(3);
		createExplicitWaitClickable(loggedIntoolTip, 10);
		uidriver.findElement(yourTripsBtn).click();

	}

	public String getloggedIntipText() {

		return uidriver.findElement(loggedIntoolTip).getText();
	}


	public String getloggedInEmail() {
		return uidriver.findElement(loggedInEmail).getText();

	}

}
