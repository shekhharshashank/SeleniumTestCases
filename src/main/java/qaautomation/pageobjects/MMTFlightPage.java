package qaautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaautomation.core.BasePage;


public class MMTFlightPage extends BasePage {

	public MMTFlightPage(WebDriver uidriver) {
		super(uidriver);
	}

	public static final String ADULTS = "ADULTS (12y +)";
	public static final String CHILDERN = "CHILDREN (2y - 12y )";
	public static final String TRAVEL_CLASS = "CHOOSE TRAVEL CLASS";
	public static final String TRAVEL_XPATH_1 = "//p[contains(.,'";
	public static final String TRAVEL_XPATH_2 = "')]//following-sibling::ul//li";

	private By fromCity = By.xpath("//input[@id='fromCity' and @type='text']");
	private By toCity = By.id("//input[@id='toCity' and @type='text']");
	private By departure = By.id("departure");
	private By fromCityDropdown = By.cssSelector("div[id*='react-autowhatever']");
	private By typeFromCity = By.cssSelector("input[class*='react-']");
	private By dropDownSuggestionfromCity = By.cssSelector("div ul [class*='react-autosuggest__suggestion']");
	private By autoPopUp = By.cssSelector("div [class*='autopop__wrap ']");
	private By movePreviousmnth = By.xpath("//*[contains(@aria-label,'Previous Month')]");
	private By searchButton = By.xpath("//a[contains(text(),'Search')]");
	private By travellersDropdown = By.xpath("//*[@id='travellers']/preceding-sibling::span");
	private By roundTrip = By.xpath("//li[contains(text(),'Round Trip')]");
	private By applyTravller = By.cssSelector("button[class*='btnApply']");
	private By adultsOptions = By.xpath("//p[contains(.,'ADULTS (12y +)')]//following-sibling::ul//li");
	private By childrenOptions = By.xpath("//p[contains(.,'CHILDREN (2y - 12y )')]//following-sibling::ul//li");

	public MMTFlightPage selectFromCity(String city) throws InterruptedException {

		createExplicitWaitClickable(fromCity, 5);
		uidriver.findElement(fromCity).click();
	
		createExplicitWait(fromCityDropdown, 3);
		uidriver.findElement(typeFromCity).clear();
		uidriver.findElement(typeFromCity).sendKeys(city);
		waitSeconds(2);
		createExplicitWait(dropDownSuggestionfromCity, 3);
		selectElementByIndex(dropDownSuggestionfromCity, 0);

		return this;

	}

	public MMTFlightPage selectToCity(String city) throws InterruptedException {

		// createExplicitWaitTillClickable(toCity,10);
		// uidriver.findElement(toCity).click();
		// Thread.sleep(4000);
		createExplicitWait(fromCityDropdown, 3);
		uidriver.findElement(typeFromCity).sendKeys(city);
		waitSeconds(2);
		createExplicitWait(dropDownSuggestionfromCity, 3);
		selectElementByIndex(dropDownSuggestionfromCity, 0);

		return this;

	}

	public void chooseDate(String dateRequired, String dateRrturn) throws InterruptedException {

		try {
			if (uidriver.findElement(By.xpath("//*[contains(@aria-label,'" + dateRequired + "')]")).isDisplayed()) {

				createExplicitWaitClickable(By.xpath(
						"//*[contains(@aria-label,'" + dateRequired + "') and contains(@aria-disabled,'false')]"), 10);
				uidriver.findElement(By.xpath(
						"//*[contains(@aria-label,'" + dateRequired + "') and contains(@aria-disabled,'false')]"))
						.click();

				Thread.sleep(2000);
				createExplicitWaitClickable(
						By.xpath(
								"//*[contains(@aria-label,'" + dateRrturn + "') and contains(@aria-disabled,'false')]"),
						10);
				uidriver.findElement(By
						.xpath("//*[contains(@aria-label,'" + dateRrturn + "') and contains(@aria-disabled,'false')]"))
						.click();

			}
		} catch (Exception e) {
			System.out.println("Element No found" + e);
			try {
				if (uidriver.findElement(movePreviousmnth).isDisplayed()
						|| uidriver.findElement(movePreviousmnth).isEnabled()) {
					uidriver.findElement(movePreviousmnth).click();
					chooseDate(dateRequired, dateRrturn);
				}
			} catch (Exception e1) {
				System.out.println("more forward date");
			}
		}

	}

	public void selectRoundTrip() {
		/*
		 * Actions actions = new Actions(uidriver);
		 * actions.moveToElement(uidriver.findElement(roundTrip)); actions.perform();
		 */
		
		
		
		createExplicitWaitClickable(roundTrip, 3);
		JavascriptExecutor executor = (JavascriptExecutor)uidriver;
		executor.executeScript("arguments[0].click();", uidriver.findElement(roundTrip));

	}

	

	public void chooseNumberofAdults(String s) {

		List<WebElement> adultsPresent;
		adultsPresent = uidriver.findElements(By.xpath(TRAVEL_XPATH_1 + ADULTS + TRAVEL_XPATH_2));

		for (WebElement ele : adultsPresent) {
			if (ele.getText().startsWith(s)) {
				ele.click();
				System.out.println(ele.getText());
				break;
			}

		}
	}

	public MMTFlightPage chooseNumberofChildren(String s) {

		List<WebElement> adultsPresent;
		adultsPresent = uidriver.findElements(By.xpath(TRAVEL_XPATH_1 + CHILDERN + TRAVEL_XPATH_2));

		for (WebElement ele : adultsPresent) {
			if (ele.getText().startsWith(s)) {
				ele.click();
				System.out.println(ele.getText());
				break;
			}

		}
		return this;
	}

	public MMTFlightPage chooseClassofFLight(String s) {

		List<WebElement> adultsPresent;
		adultsPresent = uidriver.findElements(By.xpath(TRAVEL_XPATH_1 + TRAVEL_CLASS + TRAVEL_XPATH_2));

		for (WebElement ele : adultsPresent) {
			if (ele.getText().startsWith(s)) {
				ele.click();
				System.out.println(ele.getText());
				break;
			}

		}
		return this;
	}

	public MMTFlightPage selecTravellers() {
		createExplicitWaitClickable(travellersDropdown, 5);
		uidriver.findElement(travellersDropdown).click();
		return this;
	}

	public MMTFlightPage applyButton() {

		createExplicitWaitClickable(applyTravller, 5);
		uidriver.findElement(applyTravller).click();
		return this;
	}

	public MMTFlightSearchPage searchButton() {

		uidriver.findElement(searchButton).click();
		return new MMTFlightSearchPage(uidriver);
	}
	
	public void refrehPage() {
		
		uidriver.navigate().refresh();
	}
	
	
	
	
	
	public MMTFlightPage removePopUpIfPresent() {

		try {
			if (uidriver.findElement(autoPopUp).isDisplayed()) {
				System.out.println("Element Present  ");
				
				JavascriptExecutor js= (JavascriptExecutor) uidriver;;
				//String popUp = "document.getElementsByClassName('autopop__wrap makeFlex column defaultCursor')[0].remove();";
				String pop2="document.getElementsByClassName('loginModal displayBlock modalLogin dynHeight personal ')[0].click();";
				js.executeScript(pop2);
				
				WebDriverWait wait3 = new WebDriverWait(uidriver, 10);
				wait3.until(ExpectedConditions.invisibilityOfElementLocated(autoPopUp));
				js.executeScript("arguments[0].scrollIntoView();",roundTrip);
				
			}
		} catch (Exception e) {
			System.out.println(autoPopUp + " Element Is Not Found");
		}

		return this;
	}
}
