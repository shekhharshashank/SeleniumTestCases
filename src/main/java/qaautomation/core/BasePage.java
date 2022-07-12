package qaautomation.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	public WebDriver uidriver;
	public static final String HOTELS = "hotels";
	public static final String FLIGHTS = "flights";
	public WebDriverWait explicitWait ;
	public JavascriptExecutor js ;

	public BasePage(WebDriver uidriver) {

		this.uidriver = uidriver;

	}

	public void navigateToPage(String page) {
		By navigationPage = By.cssSelector("div.chHeaderContainer a[href$='" + page + "/']");
		String newPage = uidriver.findElement(navigationPage).getAttribute("href");

		if (!newPage.isEmpty()) {
			uidriver.navigate().to(newPage);
		}
	}

	public void createExplicitWait(By element, int seconds) {
		explicitWait = new WebDriverWait(uidriver, seconds);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void createExplicitWaitClickable(By element, int seconds) {
		 explicitWait = new WebDriverWait(uidriver, seconds);
		 explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void createExplicitWaitClickable(WebElement element, int seconds) {
		 explicitWait = new WebDriverWait(uidriver, seconds);
		 explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void selectElementByIndex(By element, int index) {
		List<WebElement> elemnts;
		elemnts = uidriver.findElements(element);
		if (index < elemnts.size()) {
			 {
			elemnts.get(index).click();}
		}

	}
	public void waitforElementInIndexClickable(By element, int index) {
		List<WebElement> elemnts;
		explicitWait = new WebDriverWait(uidriver, 10);
		elemnts = uidriver.findElements(element);
		if (index < elemnts.size()) {
			 {
			explicitWait.until(ExpectedConditions.elementToBeClickable(elemnts.get(index)));

			}
		}

	}

	public void waitforElementToHaveText(By element, int index,String text) {
		List<WebElement> elemnts;
		explicitWait = new WebDriverWait(uidriver, 10);
		elemnts = uidriver.findElements(element);
		if (index < elemnts.size()) {
			 {

				 explicitWait.until(ExpectedConditions.textToBePresentInElement(elemnts.get(index), text));

			}
		}

	}



	public static void waitSeconds(int i) throws InterruptedException {

		Thread.sleep(1000 * i);
	}

	public void clickAsJavaScript(By ele) {
		JavascriptExecutor executor = (JavascriptExecutor)uidriver;
		executor.executeScript("arguments[0].click();", uidriver.findElement(ele));
	}


}
