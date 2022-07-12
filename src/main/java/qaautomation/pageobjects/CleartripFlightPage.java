package qaautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qaautomation.core.BasePage;

public class CleartripFlightPage extends BasePage {

	public CleartripFlightPage(WebDriver uidriver) {
		super(uidriver);
	}

	private By fromCity=By.xpath("//h4[contains(text(),'From')]/..//div//input");
	private By fromCitySuggestions=By.cssSelector("div [class^='bg-white br-4'] ul li");
	private By toCity=By.xpath("//h4[contains(text(),'To')]/..//div//input");
	private By toCitySuggestions=By.cssSelector("div [class^='bg-white br-4'] ul li");
	private By chooseDates=By.cssSelector("div[class$='homeCalender'] button");
	private By toCitySuggestionsLoaded=By.cssSelector("div [class^='bg-white br-4'] ul");
	private By moreForwardMonth=By.cssSelector("[data-testid='rightArrow']");
	private By morePreviousMonth=By.cssSelector("[data-testid='leftArrow']");
	private By departsOn=By.xpath("//h4[contains(text(),'Depart on')]");

	public void selectDates(String boradingDate, String dateRrturn) throws InterruptedException {

		List<WebElement> dates=uidriver.findElements(chooseDates);

		if(!dates.isEmpty()) {

			createExplicitWaitClickable(dates.get(0), 10);
			dates.get(0).click();
			chooseDate(boradingDate,dateRrturn);

		}

	}



	public CleartripFlightPage selectAndenterDestinationCity(String cityName) throws InterruptedException {

		createExplicitWaitClickable(toCity, 10);
		uidriver.findElement(toCity).click();
		uidriver.findElement(toCity).clear();
		uidriver.findElement(toCity).sendKeys(cityName);
		waitSeconds(2);
		waitforElementToHaveText(toCitySuggestionsLoaded, 0,cityName);
		selectElementByIndex(toCitySuggestions, 0);

		return this;

	}

	public CleartripFlightPage selectAndenterBoardingCity(String cityName) throws InterruptedException {

		createExplicitWaitClickable(fromCity, 10);
		uidriver.findElement(fromCity).click();
		uidriver.findElement(fromCity).clear();
		uidriver.findElement(fromCity).sendKeys(cityName);

		waitSeconds(2);
		waitforElementInIndexClickable(fromCitySuggestions, 0);
		selectElementByIndex(fromCitySuggestions, 0);

		return this;

	}

	public CleartripFlightPage selectTripType(String tripType) throws InterruptedException {

		List<WebElement> tripTypes = uidriver.findElements(By.cssSelector("label[class$='radio__secondary']"));

		for (WebElement anc : tripTypes) {
			if (anc.getText().toLowerCase().contains(tripType.toLowerCase())) {
				anc.click();


			}

		}

		return this;
	}

	public void chooseDate(String dateRequired, String dateRrturn) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor)uidriver;
		js.executeScript("arguments[0].scrollIntoView();",departsOn);

		try {
			if (uidriver.findElement(By.xpath("//*[contains(@aria-label,'" + dateRequired + "')]")).isDisplayed()) {

				createExplicitWaitClickable(By.xpath(
						"//*[contains(@aria-label,'" + dateRequired + "')]"), 10);
				uidriver.findElement(By.xpath(
						"//*[contains(@aria-label,'" + dateRequired + "')]//div//div"))
						.click();

				createExplicitWaitClickable(
						By.xpath(
								"//*[contains(@aria-label,'" + dateRrturn + "')]"),
						10);
				uidriver.findElement(By
						.xpath("//*[contains(@aria-label,'" + dateRrturn + "')]//div//div"))
						.click();

			}
		} catch (Exception e) {
			System.out.println("Element No found" + e);
			try {
				if (uidriver.findElement(moreForwardMonth).isDisplayed()
						|| uidriver.findElement(moreForwardMonth).isEnabled()) {
					uidriver.findElement(moreForwardMonth).click();
					chooseDate(dateRequired, dateRrturn);
				}
			} catch (Exception e1) {
				System.out.println("more forward date");
			}
		}

	}


}
