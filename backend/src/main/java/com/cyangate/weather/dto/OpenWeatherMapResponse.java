package com.cyangate.weather.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OpenWeatherMapResponse
{
  public Coord coord;
  public List<Weather> weather;
  public String base;
  public Main main;
  public int visibility;
  public Wind wind;
  public Clouds clouds;
  public int dt;
  public Sys sys;
  public int timezone;
  public int id;
  public String name;
  public int cod;
}
