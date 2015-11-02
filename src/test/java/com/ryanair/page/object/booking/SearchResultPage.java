package com.ryanair.page.object.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ryanair.page.object.base.BasePage;

public class SearchResultPage extends BasePage {
	
	//locators
	private final By continueButtonLocator = By.id("SelectInput_ButtonSubmit");
	private final By toDirectionLocator = By.xpath(".//article[contains(@class, 'selectFlights')][1]//h1");
	private final By fromDirectionLocator = By.xpath(".//article[contains(@class, 'selectFlights')][2]//h1");
	private final By activeDepartureDayLocator = By.xpath(".//ol[@id='tabs1']//a[@class='active']//time");
	private final By activeArrivalDayLocator = By.xpath(".//ol[@id='tabs2']//a[@class='active']//time");
	private final By activeDepartureLocator = By.xpath(".//ol[@id='tabs1']//a[@class='active']");
	private final By activeArrivalLocator = By.xpath(".//ol[@id='tabs2']//a[@class='active']");
		
	public SearchResultPage(WebDriver driver) {
			super(driver);
	}
	
	public boolean continueButtonDisabled() {
		boolean result = false;
		try {
	        String value = driver.findElement(continueButtonLocator)
				      .getAttribute("disabled");
	        if (!value.equals(null)) {
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	
	public void continueBooking() {
		driver.findElement(continueButtonLocator).click();
		waitForPageLoaded();
	}
	
	public String getToDirectionDescription() {
		return driver.findElement(toDirectionLocator).getText();
	}
	
	public String getFromDirectionDescription() {
		return driver.findElement(fromDirectionLocator).getText();
	}
	
	public String getDepartureDate() {
		return driver.findElement(activeDepartureDayLocator).getText().split(",")[1].trim();
	}
	
	public String getArrivalDate() {
		return driver.findElement(activeArrivalDayLocator).getText().split(",")[1].trim();
	}
	
	public boolean isDepartureBookingAvailable() {
		String departureDescription = driver.findElement(activeDepartureLocator).getText();
		return !(departureDescription.contains("Sold out") 
				 || departureDescription.contains("No flight"));
	}
	
	public boolean isArrivalBookingAvailable() {
		String arrivalDescription = driver.findElement(activeArrivalLocator).getText();
		return !(arrivalDescription.contains("Sold out") 
				 || arrivalDescription.contains("No flight"));
	}

}
