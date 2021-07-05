package com.vanguard.WeatherApi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanguard.WeatherApi.Controller.WeatherController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApiApplication.class)
public class WeatherControllerTest {

    @Autowired
    WeatherController weatherController;

    @Test
    public void testGetWeatherForCity_success() throws JsonProcessingException {
        String weatherDescription = weatherController.getWeather("london", "uk");
        Assert.assertNotNull(weatherDescription);
    }

    @Test
    public void testGetWeatherForCity_failure() throws JsonProcessingException {
        String weatherDescription = weatherController.getWeather("fakeCity", "uk");
        Assert.assertTrue(weatherDescription.contains("city not found"));
    }

    @Test
    public void testGetWeatherForCity_success_withCityOnly() throws JsonProcessingException {
        String weatherDescription = weatherController.getWeather("london", null);
        Assert.assertNotNull(weatherDescription);
    }

    @Test
    public void testGetWeatherForCity_failure_withCountryOnly() throws JsonProcessingException {
        String weatherDescription = weatherController.getWeather(null,"uk");
        Assert.assertTrue(weatherDescription.equals("Missing city name"));
    }

    @Test
    public void testGetWeatherForCity_testRateLimiting() throws JsonProcessingException {
        String weatherDescription1 = weatherController.getWeather("london", "uk");
        String weatherDescription2 = weatherController.getWeather("brussels", "be");
        String weatherDescription3 = weatherController.getWeather("tokyo", "jp");
        String weatherDescription4 = weatherController.getWeather("paris", "fr");
        String weatherDescription5 = weatherController.getWeather("boston", "us");
        String weatherDescription6= weatherController.getWeather("miami", "us");
        Assert.assertTrue(weatherDescription6.equals("The hourly limit has been exceeded"));
    }
}
