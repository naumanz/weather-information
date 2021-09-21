package com.cyangate.weather;

import com.cyangate.weather.config.OpenWeatherMapProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OpenWeatherMapProperties.class)
public class WeatherApplication
{
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}
}
