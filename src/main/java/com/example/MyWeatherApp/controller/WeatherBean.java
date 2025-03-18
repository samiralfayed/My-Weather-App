package com.example.MyWeatherApp.controller;


import com.example.MyWeatherApp.model.WeatherData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("weatherBean") // Replaces @ManagedBean
@SessionScoped // Keeps data during session
@Component
public class WeatherBean implements Serializable {
    // Getters & Setters
    @Setter
    @Getter
    private String city;
    @Getter
    private WeatherData weatherData;
    private final RestTemplate restTemplate = new RestTemplate();

    public void fetchWeather() {
        String apiUrl = "http://localhost:8080/api/weather/" + city;
        weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);
    }

}