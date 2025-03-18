package com.example.MyWeatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWeatherAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyWeatherAppApplication.class, args);

		System.out.println("HI. This is my Weather App");
	}

}
