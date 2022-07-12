package qaautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaautomation.core.BasePage;

public class MMTHomePage extends BasePage {
	
	private By autoPopUp = By.cssSelector("div [class*='autopop__wrap ']");
	private By loginButton=By.cssSelector("div [class*='makeFlex column flexOne whiteText latoBold']");
	private By enterUserName=By.cssSelector("input#username");
	private By continueBtn=By.xpath("//span[starts-with(text(),'Continue')]");
	private By passwordEntrBtn=By.id("password");
	private By loginBtn=By.xpath("//span[starts-with(text(),'Login')]");
	
public void enterPassword(String password) {
		
		createExplicitWait(passwordEntrBtn, 6);
		uidriver.findElement(passwordEntrBtn).sendKeys(password);
		
		createExplicitWaitClickable(loginBtn,6);
		uidriver.findElement(loginBtn).click();
	}
	
	public MMTHomePage enterUserName(String userName) {
		
		createExplicitWait(enterUserName, 3);
		uidriver.findElement(enterUserName).sendKeys(userName);
		
		createExplicitWaitClickable(continueBtn, 3);
		uidriver.findElement(continueBtn).click();
		return this;
	}
	
	public MMTHomePage(WebDriver uidriver) {
		super(uidriver);
	}

	public MMTHotelPage navigateToGetHotelPage() {
		navigateToPage(HOTELS);
		return new MMTHotelPage(uidriver);
	}
	
	public MMTFlightPage navigateToGetFlightsPage() {
		navigateToPage(FLIGHTS);
		return new MMTFlightPage(uidriver);
	}

	
	
	public MMTHomePage clickOnLoginButton() {
		uidriver.findElement(loginButton).click();
		return this;
	}

	
	public MMTHomePage closePopupIfPresent() {

		try {
			if (uidriver.findElement(autoPopUp).isDisplayed()) {
				System.out.println("Pop Up is Present .......  ");
				js= (JavascriptExecutor) uidriver;;
				js.executeScript("document.getElementsByClassName('loginModal displayBlock modalLogin dynHeight personal ')[0].click();");
				explicitWait= new WebDriverWait(uidriver, 10);
				explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(autoPopUp));
			}
		} catch (Exception e) {
			System.out.println(autoPopUp + " Element Is Not Found");
		}
		
		return this;
	}
}
