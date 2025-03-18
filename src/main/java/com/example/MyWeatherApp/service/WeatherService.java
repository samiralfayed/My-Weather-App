package com.example.MyWeatherApp.service;

import com.example.MyWeatherApp.model.WeatherData;
import java.util.Optional;

public interface WeatherService {
    Optional<WeatherData> fetchWeather(String city);
}
