package com.vanguard.WeatherApi.Service;

import com.vanguard.WeatherApi.Dao.WeatherResponseRepo;
import com.vanguard.WeatherApi.Model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherResponseRepo weatherResponseRepo;

    @Override
    public String getWeatherDetails(String city, String country) {
        int index = 4;
        final String[] apiKeys = {"c59319dae4ca3e43de917ef73ada2856", "31010b4c240ec107a06f1154d6e0ff59", "9dc140803c3081b7e214763e4a3cfb6a", "9deca4b672a20044d75118b5c7ab2a83", "4b17c8e9bce01792dbe002df9c233823"};
        final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&APPID=" + apiKeys[index];
        index--;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WeatherResponse> response = null;
        try {
            response = restTemplate
                    .getForEntity(url, WeatherResponse.class);
        } catch  (HttpClientErrorException ex) {
            return (ex.getResponseBodyAsString());
        }

        WeatherResponse wr = response.getBody();
        weatherResponseRepo.save(wr);
        return getWeatherDescriptionText(wr.id);
    }

    public String getWeatherDescriptionText(int id) {
        return weatherResponseRepo.findById(id).get().weather.get(0).description;
    }
}
