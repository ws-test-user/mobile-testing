package demo_04_restassured.tests;

import demo_04_restassured.OpenWeatherAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenWeatherApiTests {

    @Test
    public void getTemperature() {
        int temp = OpenWeatherAPI.getTemperature("Sofia,bg");
        Assert.assertTrue(temp < 50, "Temperature is too high!");
        Assert.assertTrue(temp > 0, "It can't be so cold!");
    }
}
