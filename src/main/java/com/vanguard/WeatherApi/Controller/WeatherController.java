package com.vanguard.WeatherApi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanguard.WeatherApi.Service.WeatherService;

import io.github.bucket4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.time.Duration;

@Controller
@Validated
public class WeatherController {

    @Autowired
    WeatherService weatherService;
    private final Bucket bucket;

    public WeatherController() {
        Bandwidth limit = Bandwidth.classic(6, Refill.greedy(5, Duration.ofMinutes(60)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/getWeather")
    @ResponseBody
    public String getWeather(@RequestParam(required = false) String city, @RequestParam(required = false) String country) throws JsonProcessingException {

        if (city == null) {
            return "Missing city name";
        }

        ConsumptionProbe consumptionProbe = bucket.tryConsumeAndReturnRemaining(1);
        if (consumptionProbe.getRemainingTokens() > 0) {
          return weatherService.getWeatherDetails(city, country);
        }
        return "The hourly limit has been exceeded";
    }
}
