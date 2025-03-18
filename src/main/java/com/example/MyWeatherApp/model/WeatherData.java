package com.example.MyWeatherApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double pressure;
    private String condition;
}