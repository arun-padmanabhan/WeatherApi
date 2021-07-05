package com.vanguard.WeatherApi.Dao;

import com.vanguard.WeatherApi.Model.WeatherResponse;
import org.springframework.data.repository.CrudRepository;

public interface WeatherResponseRepo extends CrudRepository<WeatherResponse, Integer> {

}
