package qaautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qaautomation.core.BasePage;

public class MMTFlightSearchPage extends BasePage{

	public MMTFlightSearchPage(WebDriver uidriver) {
		super(uidriver);
	}
	
	private By flightOptions=By.xpath("[class^='listingCard'] div label div[class^='listingCard']:nth-child(1) [class^='inner']");
	
	private By startflightsOption= By.xpath("//*[@class='paneView'][1]//span[@class='inner']");
	
	private By returnflightsOption= By.xpath("//*[@class='paneView'][2]//span[@class='inner']");
	private By bookNow=By.cssSelector("button[id^=bookbutton]");
	
	public void selectstartFlight() {
		
		List<WebElement> strtFlight=uidriver.findElements(startflightsOption);
		JavascriptExecutor js = (JavascriptExecutor) uidriver;
		
		js.executeScript("arguments[0].scrollIntoView()", strtFlight.get(1)); 
		
		//js.executeScript("window.scrollBy(0,180)", "");
		if(!strtFlight.get(1).isSelected()) {
		js.executeScript("arguments[0].click();",strtFlight.get(1));
		}
		else{
			System.out.println("It's Already Selected");
		}
		
	}
	
		public void selectreturnFlight() {
		
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) uidriver;
		 * js.executeScript("window.scrollBy(0,350)", "");
		 */
		List<WebElement> retFlight=uidriver.findElements(returnflightsOption);
		JavascriptExecutor js = (JavascriptExecutor) uidriver;
		js.executeScript("arguments[0].click();",retFlight.get(1));
		
		
	}
		
		public MMTFlightSearchPage bookNowchoosenFlights() {
			
			JavascriptExecutor js = (JavascriptExecutor) uidriver;
			js.executeScript("arguments[0].click();",uidriver.findElement(bookNow));
		
			
			return this;
		}
	
	
	
	
	
	

}
