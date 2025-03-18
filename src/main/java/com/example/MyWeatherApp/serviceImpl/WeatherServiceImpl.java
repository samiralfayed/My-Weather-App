package com.example.MyWeatherApp.serviceImpl;

import com.example.MyWeatherApp.model.WeatherData;
import com.example.MyWeatherApp.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON parser

    @Override
    public Optional<WeatherData> fetchWeather(String city) {
        try {
            // Fetch latitude and longitude
            String locationUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + city;
            String locationResponse = restTemplate.getForObject(locationUrl, String.class);
            JsonNode locationArray = objectMapper.readTree(locationResponse);

            if (locationArray.isEmpty()) {
                return Optional.empty();
            }

            double latitude = locationArray.get(0).get("lat").asDouble();
            double longitude = locationArray.get(0).get("lon").asDouble();

            // Fetch weather data
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                    + "&longitude=" + longitude
                    + "&hourly=temperature_2m,wind_speed_10m,relative_humidity_2m,pressure_msl&timezone=auto";

            String weatherResponse = restTemplate.getForObject(weatherUrl, String.class);
            JsonNode weatherNode = objectMapper.readTree(weatherResponse);

            double temperature = weatherNode.get("hourly").get("temperature_2m").get(0).asDouble();
            double humidity = weatherNode.get("hourly").get("relative_humidity_2m").get(0).asDouble();
            double windSpeed = weatherNode.get("hourly").get("wind_speed_10m").get(0).asDouble();
            double pressure = weatherNode.get("hourly").get("pressure_msl").get(0).asDouble();

            // Determine weather condition
            String condition = (temperature < 10) ? "Cold" :
                    (temperature < 25) ? "Cool" :
                            (temperature < 29) ? "Warm" : "Hot";

            return Optional.of(new WeatherData(temperature, humidity, windSpeed, pressure, condition));

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}