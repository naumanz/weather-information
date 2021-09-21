package com.cyangate.weather.dao;

import com.cyangate.weather.entity.Location;
import com.cyangate.weather.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Implementation class of Weather dao interface
 */
@Component
@RequiredArgsConstructor
public class WeatherDaoImpl implements WeatherDaoI
{

  public final LocationRepository locationRepository;

  @Override
  public Location getLocation(String city, String country)
  {
    return locationRepository.findByCityNameAndCountryName(city, country);
  }
}
