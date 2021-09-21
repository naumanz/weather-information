package com.cyangate.weather.dao;

import com.cyangate.weather.entity.Location;

/**
 * Weather Dao interface
 */
public interface WeatherDaoI
{
  Location getLocation(String city, String country);
}
