package demo_04_restassured;


import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

/**
 * Wrapper around OpenWeatherMap REST Api.
 * <p>
 * API Docs:
 * http://openweathermap.org/api
 * <p>
 * Usage:
 * In order to use the api you will need API_KEY.
 * 1. Register account
 * 2. Get API_KEY from https://home.openweathermap.org/api_keys
 * 3. Set OPEN_WEATHER_API_KEY environment variable (set value to your API_KEY)
 */
public class OpenWeatherAPI {

    public static int getTemperature(String location) {

        // Get value of API_KEY environment variable
        String apiKey = System.getenv("OPEN_WEATHER_API_KEY");

        // Set base url for all RestAssured requests in this class
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";

        // Enable logging on request/response failure
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Get temperature in Sofia via rest api
        int tempFromApiInt =
                // Actual request is:
                // http://api.openweathermap.org/data/2.5/weather?q=<location>&appid=<api_key>&units=metric
                // queryParam are used to specify query params like appid and units
                given().
                        queryParam("q", location).
                        queryParam("units", "metric").
                        queryParam("appid", apiKey).
                        when().
                        get().
                        then().
                        statusCode(200). // Assert Status code is 200
                        extract().
                        path("main.temp"); // Extract temperature from response body
        return tempFromApiInt;
    }
}
