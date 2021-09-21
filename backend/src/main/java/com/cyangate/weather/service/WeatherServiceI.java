package com.cyangate.weather.service;

import com.cyangate.weather.dto.WeatherResponse;

import java.util.Optional;

/**
 * Weather service interface which will make call to data layer
 */
public interface WeatherServiceI
{
  Optional<WeatherResponse> getWeatherDetail(String city, String country);
}
