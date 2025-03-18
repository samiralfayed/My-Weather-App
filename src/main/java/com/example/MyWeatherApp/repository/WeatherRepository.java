package com.example.MyWeatherApp.repository;

import com.example.MyWeatherApp.model.WeatherData;
import java.util.Optional;

public interface WeatherRepository {
    Optional<WeatherData> getWeatherByCity(String city);
}