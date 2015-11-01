package flightsTesting.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import flightsTesting.baseUi.BasePage;
import flightsTesting.core.Flight;
import flightsTesting.utils.Utilities;

public class FlightSearchPage extends BasePage {
	//fields
	public final String url = "https://www.bookryanair.com/SkySales/Booking.aspx#Search";
	
	//locators
	private final By originSelectLocator = By.xpath(".//select[@title='Origin']");
	private final By destinationSelectLocator = By.xpath(".//select[@title='Destination']");
	private final By adultsNumberSelectLocator = By.xpath(".//select[@title='Adult']");
	private final By teensNumberSelectLocator = By.xpath(".//select[@title='Teen']");
	private final By childrenNumberSelectLocator = By.xpath(".//select[@title='Child']");
	private final By infantsNumberSelectLocator = By.xpath(".//select[@title='Infant']");
	private final By outDateInputLocator = By.xpath(".//input[@name='SearchInput$DeptDate']");
	private final By backDateInputLocator = By.xpath(".//input[@name='SearchInput$RetDate']");
	private final By searchButtonLocator = By.id("SearchInput_ButtonSubmit");
	
	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}
	
	private void findItemInSelect(By itemLocator, String text) {
		WebElement selectElement = driver.findElement(itemLocator);
		Select dropDown = new Select(selectElement);
		dropDown.selectByValue(text);
	}
	
	private String getSelectedOption(By itemLocator) {
		WebElement selectElement = driver.findElement(itemLocator);
		Select dropDown = new Select(selectElement);
		return dropDown.getFirstSelectedOption().getText();
	}
	
	private void insertDate(By itemLocator, String date) {
		WebElement dataInput = driver.findElement(itemLocator);
		dataInput.click();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		dataInput.sendKeys(selectAll);
		dataInput.sendKeys(date);
	}
	
	public void  Navigate() {
		driver.get(url);
	}
	
	public void setOrigin(String origin) {
		findItemInSelect(originSelectLocator, origin);		
	}
	
	public void setDestination(String destination) {
		findItemInSelect(destinationSelectLocator, destination);	
	}
	
	public void setAdultsNumber(Integer adultsNumber) {
		findItemInSelect(adultsNumberSelectLocator, String.valueOf(adultsNumber));
	}
	
	public void setTeensNumber(Integer teensNumber) {
		findItemInSelect(teensNumberSelectLocator, String.valueOf(teensNumber));
	}
	
	public void setChildrenNumber(Integer childrenNumber) {
		findItemInSelect(childrenNumberSelectLocator, String.valueOf(childrenNumber));		
	}
	
	public void setInfantsNumber(Integer infantsNumber) {
		findItemInSelect(infantsNumberSelectLocator, String.valueOf(infantsNumber));
	}
	
	public void insertOutDate(String date) {
		insertDate(outDateInputLocator, date);
	}
	
	public void insertBackDate(String date) {
		insertDate(backDateInputLocator, date);
	}
	
	public void performSearch() {
		WebElement searchButton = driver.findElement(searchButtonLocator);
		searchButton.click();
		waitForPageLoaded();
	}
	
	public String getSelectedOrigin(){
		String fullOrigin = getSelectedOption(originSelectLocator);
		return fullOrigin.substring(0, fullOrigin.length() - 6);
	}
	
	public String getSelectedDestination(){
		String fullDestination = getSelectedOption(destinationSelectLocator);
		return fullDestination.substring(0, fullDestination.length() - 6);
	}
	
	public void fillSearchRequest(Flight flight) {
		setAdultsNumber(Utilities.getAdultsNumber(flight.getPassengers()));
		setTeensNumber(Utilities.getTeensNumber(flight.getPassengers()));
		setChildrenNumber(Utilities.getChildrenNumber(flight.getPassengers()));
		setInfantsNumber(Utilities.getInfantsNumber(flight.getPassengers()));
		setOrigin(flight.getFrom());
		setDestination(flight.getTo());
		flight.setFrom(getSelectedOrigin());
		flight.setTo(getSelectedDestination());
		insertOutDate(flight.getDepartureDate());
		insertBackDate(flight.getArrivalDate());
	}

}
