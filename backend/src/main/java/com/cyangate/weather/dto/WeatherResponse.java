package com.cyangate.weather.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WeatherResponse
{

  @Setter
  private String city;
  @Setter
  private String country;

  private List<WeatherInfo> weather;

  public void addWeather(final WeatherInfo weatherInfo)
  {

    if (weather == null)
    {
      weather = new ArrayList<>();
    }
    weather.add(weatherInfo);
  }
}
