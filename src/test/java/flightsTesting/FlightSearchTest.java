package flightsTesting;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
 
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/flightTesting/test")
public class FlightSearchTest {

}
