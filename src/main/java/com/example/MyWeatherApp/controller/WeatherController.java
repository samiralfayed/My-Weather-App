package com.example.MyWeatherApp.controller;

import com.example.MyWeatherApp.model.WeatherData;
import com.example.MyWeatherApp.service.WeatherService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public Optional<WeatherData> getWeather(@PathVariable String city) {
        return weatherService.fetchWeather(city);
    }
}

