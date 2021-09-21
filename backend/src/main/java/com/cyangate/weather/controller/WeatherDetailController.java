package com.cyangate.weather.controller;

import com.cyangate.weather.dto.WeatherResponse;
import com.cyangate.weather.service.WeatherServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * The main controller class which returns the weather details
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherDetailController
{

  private final WeatherServiceI weatherService;

  /**
   * End point which returns the weather detail of specific city and country
   */
  @GetMapping(value = "/weather/details")
  public ResponseEntity<WeatherResponse> getWeatherDetails(@RequestParam String city, @RequestParam String country)
  {
    log.info("Getting the weather information for city {}, country {}", city, country);

    Optional<WeatherResponse> response = weatherService.getWeatherDetail(city, country);
    if (response.isPresent()) {
      return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
    return ResponseEntity.noContent().build();
  }
}
