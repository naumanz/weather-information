package com.cyangate.weather.service;

import com.cyangate.weather.dao.WeatherDaoI;
import com.cyangate.weather.dto.WeatherInfo;
import com.cyangate.weather.dto.WeatherResponse;
import com.cyangate.weather.entity.Location;
import com.cyangate.weather.entity.WeatherDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of WeatherService interface which fetch the data from data access layer
 */
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherServiceI
{
  private final WeatherDaoI weatherDao;

  @Override
  public Optional<WeatherResponse> getWeatherDetail(String city, String country)
  {
    Location location = weatherDao.getLocation(city, country);

    if (location != null)
    {
      return Optional.of(transformDaoToModel(location));
    }

    return Optional.empty();
  }

  private WeatherResponse transformDaoToModel(Location location)
  {
    WeatherResponse weatherResponse = new WeatherResponse();
    weatherResponse.setCity(location.getCityName());
    weatherResponse.setCountry(location.getCountryName());

    for (WeatherDetails weatherDetails : location.getWeatherDetails())
    {
      WeatherInfo weatherInfo = getWeatherInfo(weatherDetails);
      weatherResponse.addWeather(weatherInfo);
    }

    return weatherResponse;
  }

  private WeatherInfo getWeatherInfo(WeatherDetails weatherDetails)
  {
    WeatherInfo weatherInfo = new WeatherInfo();

    weatherInfo.setTemp(weatherDetails.getTemp().intValue());
    weatherInfo.setTemp_min(weatherDetails.getTemp_min().intValue());
    weatherInfo.setTemp_max(weatherDetails.getTemp_max().intValue());
    weatherInfo.setFeels_like(weatherDetails.getFeels_like().intValue());
    weatherInfo.setActionDateTime(weatherDetails.getActionDateTime());
    weatherInfo.setHumidity(weatherDetails.getHumidity());
    weatherInfo.setPressure(weatherDetails.getPressure());
    weatherInfo.setDescription(weatherDetails.getDescription());

    return weatherInfo;
  }
}
