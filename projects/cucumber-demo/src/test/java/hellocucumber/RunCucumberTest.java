package hellocucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/hellocucumber/CalcOperations.feature"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
