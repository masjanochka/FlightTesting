package flightsTesting.test;
import flightsTesting.core.Flight;
import flightsTesting.core.Passenger;
import flightsTesting.pageObjects.FlightOptionsPage;
import flightsTesting.pageObjects.FlightSearchPage;
import flightsTesting.pageObjects.SearchResultPage;
import flightsTesting.utils.Utilities;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlightSearchStepsDefinition {
	
	WebDriver driver;
	Flight flight;
	FlightSearchPage searchPage;
	SearchResultPage resultPage;
	FlightOptionsPage optionsPage;
	boolean bookingIsPossible = false;
	
	@Before
	public void initializeDriver() {
		driver = new ChromeDriver();
		searchPage = new FlightSearchPage(driver);
		searchPage.Navigate();
	}	
	
	@Given("^A list of passengers$")
	public void a_list_of_passengers(List<Passenger> passengers) throws Throwable {
		flight = new Flight();
		flight.setPassengers(passengers);		
	}

	@When("^Searched for flights from (.*) to (.*) out (.*) back (.*)$")
	public void searched_for_flights(String from, String to, String outDate, String backDate) throws Throwable {
		flight.setFrom(from);
		flight.setTo(to);
		flight.setDepartureDate(Utilities.rearrangeDate(outDate));
		flight.setArrivalDate(Utilities.rearrangeDate(backDate));
		searchPage.fillSearchRequest(flight);		
		searchPage.performSearch();
	}
	
	@Then("^I should only see round trip flights for selected directions$")
	public void i_should_only_see_round_trip_flights_for_selected_directions() throws Throwable {
		resultPage = new SearchResultPage(driver);
	    String expectedOutFlight = flight.getFrom() + " → " + flight.getTo();
	    String expectedBackFlight = flight.getTo() + " → " + flight.getFrom();
	    assertThat(resultPage.getToDirectionDescription()).isEqualTo(expectedOutFlight);
	    assertThat(resultPage.getFromDirectionDescription()).isEqualTo(expectedBackFlight);
	}
	
	@Then("^Departure date should be selected$")
	public void departure_date_should_be_selected() throws Throwable {
		DateFormat format = new SimpleDateFormat("dd/MM");
		Date expectedDate = format.parse(flight.getDepartureDate());
		format = new SimpleDateFormat("MMM d");
		Date displayedDate = format.parse(resultPage.getDepartureDate());
		assertThat(displayedDate).isEqualTo(expectedDate);
	}
	
	@Then("^Arrival date should be selected$")
	public void arrival_date_should_be_selected() throws Throwable {
		DateFormat format = new SimpleDateFormat("dd/MM");
		Date expectedDate = format.parse(flight.getArrivalDate());
		format = new SimpleDateFormat("MMM d");
		Date displayedDate = format.parse(resultPage.getArrivalDate());
		assertThat(displayedDate).isEqualTo(expectedDate);
	}
	
	@Then("If there are available seats It should be possible to book them")
	public void it_should_be_possible_to_book_them() throws Throwable {
	    if(resultPage.isDepartureBookingAvailable() && resultPage.isArrivalBookingAvailable()){
	    	resultPage.scrollPage();
	    	assertThat(resultPage.continueButtonDisabled()).isFalse();
	    	bookingIsPossible = true;
	    }
	}

	@When("^Booking continued$")
	public void booking_continued() throws Throwable {
		if(bookingIsPossible) {
			resultPage.continueBooking();
		}
		
	}

	@Then("^(.*) page should be opened$")
	public void services_page_should_be_opened(String activePage) throws Throwable {
		optionsPage = new FlightOptionsPage(driver);
		if(bookingIsPossible) {			
			assertThat(optionsPage.activeMenuItemName().toLowerCase())
			           .isEqualTo(activePage.toLowerCase());
		}
	}

	@Then("^Personal information for all passengers should be requested$")
	public void personal_information_for_all_passengers_should_be_requested() throws Throwable {
		if(bookingIsPossible) {
			assertThat(optionsPage.getNumberOfPassengers()).isEqualTo(flight.getPassengers().size());
		}
	}

	@When("^Information privided$")
	public void information_privided() throws Throwable {
		if(bookingIsPossible) {
			optionsPage.fillPassengersData(flight.getPassengers());	
			optionsPage.continueBooking();
		}
	}

	@Then("^(.*) page should be displayed$")
	public void payment_page_should_be_displayed(String activePage) throws Throwable {
		if(bookingIsPossible) {			
			assertThat(optionsPage.activeMenuItemName().toLowerCase())
	           .isEqualTo(activePage.toLowerCase());
		}
	}
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
}
