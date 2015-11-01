package com.ryanair.page.object.booking;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ryanair.common.core.Passenger;
import com.ryanair.page.object.base.BasePage;

public class FlightOptionsPage extends BasePage {
	
	//locators
	private final By passengersDataBlockLocator = By.id("passengers");
	private final By continueButtonLocator = By.id("GrpServices_ButtonSubmit");
	
	private final String passengerTitleId = "title_";
	private final String passengerFirstNameId = "firstname_";
	private final String passengerLastNameId = "lastname_";
	
	public FlightOptionsPage(WebDriver driver) {
		super(driver);
	}
	
	public Integer getNumberOfPassengers() {
		return driver.findElement(passengersDataBlockLocator)
				.findElements(By.tagName("fieldset")).size();
	}
	
	public void fillPassengersData(List<Passenger> passengers)
	{
		for(Integer i = 0; i < passengers.size(); ++i ) {
			WebElement selectElement = driver.findElement(By.id(passengerTitleId + String.valueOf(i)));
			Select dropDown = new Select(selectElement);
			dropDown.selectByValue(String.valueOf(passengers.get(i).getTitle()));
			WebElement firstNameElement = driver.findElement(By.id(passengerFirstNameId + String.valueOf(i)));
			firstNameElement.sendKeys(passengers.get(i).getFirstName());
			WebElement lastNameElement = driver.findElement(By.id(passengerLastNameId + String.valueOf(i)));
			lastNameElement.sendKeys(passengers.get(i).getLastName());
		}
	}
	
	public void continueBooking() {
		scrollPage();
		driver.findElement(continueButtonLocator).click();
		waitForPageLoaded();
	}
}
