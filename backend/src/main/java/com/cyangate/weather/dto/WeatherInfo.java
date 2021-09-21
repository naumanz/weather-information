package com.cyangate.weather.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WeatherInfo
{
  private int temp;
  private int feels_like;
  private int temp_min;
  private int temp_max;
  private int pressure;
  private int humidity;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime actionDateTime;
  private String description;
}
