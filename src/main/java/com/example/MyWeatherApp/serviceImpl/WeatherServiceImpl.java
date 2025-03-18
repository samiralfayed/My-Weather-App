package com.example.MyWeatherApp.serviceImpl;

import com.example.MyWeatherApp.model.WeatherData;
import com.example.MyWeatherApp.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<WeatherData> fetchWeather(String city) {
        String locationUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + city;
        var locationResponse = restTemplate.getForObject(locationUrl, Object[].class);

        if (locationResponse == null || locationResponse.length == 0) {
            return Optional.empty();
        }

        double latitude = 23.8103; // Default latitude
        double longitude = 90.4125; // Default longitude

        String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m,wind_speed_10m,relative_humidity_2m,pressure_msl&timezone=auto";
        var weatherResponse = restTemplate.getForObject(weatherUrl, Object.class);

        // Assume parsing logic here
        double temperature = 20.0;
        double humidity = 65.0;
        double windSpeed = 10.0;
        double pressure = 1013.0;

        String condition;
        if (temperature < 10) {
            condition = "Cold";
        } else if (temperature < 25) {
            condition = "Cool";
        } else if (temperature < 29) {
            condition = "Warm";
        } else {
            condition = "Hot";
        }

        return Optional.of(new WeatherData(temperature, humidity, windSpeed, pressure, condition));
    }
}