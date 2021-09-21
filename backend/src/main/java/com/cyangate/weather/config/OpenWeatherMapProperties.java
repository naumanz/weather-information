package com.cyangate.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class which holds the openweathermap api parameters
 */
@Configuration
@ConfigurationProperties(prefix = "openweathermap")
@Getter
@Setter
public class OpenWeatherMapProperties
{
  private String apiKey;
  private String unit;
  private String delay;
  private String timeUnit;
}
