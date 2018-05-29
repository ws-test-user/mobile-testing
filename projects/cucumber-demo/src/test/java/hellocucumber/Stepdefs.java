package hellocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Stepdefs {

    @Given("^clean calculator$")
    public void clean_calculator() {
        Assert.assertTrue(true);
    }

    @When("^I sum (\\d+) and (\\d+)$")
    public void i_sum_and(int arg1, int arg2) {
        Assert.assertTrue(true);
    }

    @Then("^result should be (\\d+)$")
    public void result_should_be(int arg1) {
        Assert.assertTrue(true);
    }

    @When("^I multiply (\\d+) and (\\d+)$")
    public void i_multiply_and(int arg1, int arg2) {
        Assert.assertTrue(true);
    }
}