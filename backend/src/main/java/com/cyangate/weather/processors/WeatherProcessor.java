package com.cyangate.weather.processors;

import com.cyangate.weather.dto.Main;
import com.cyangate.weather.dto.OpenWeatherMapResponse;
import com.cyangate.weather.entity.Location;
import com.cyangate.weather.entity.WeatherDetails;
import com.cyangate.weather.repository.LocationRepository;
import com.cyangate.weather.util.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;

/**
 * The main camel processor which converts the openWeatherMap response to WeatherDetails entity
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherProcessor implements Processor
{

  private final LocationRepository locationRepository;

  @Override
  public void process(Exchange exchange) throws Exception
  {
    Long locationId = (Long) exchange.getIn().getHeader("LocationId");

    log.info("Processing the location with Id {}", locationId);

    String response = (String) exchange.getIn().getBody();
    Location locationEntity = locationRepository.getById(locationId);

    WeatherDetails weatherDetails = convertWeatherResponseToEntity(Utils.toModel(response));
    weatherDetails.setLocation(locationEntity);

    exchange.getIn().setBody(weatherDetails);
  }

  private WeatherDetails convertWeatherResponseToEntity(OpenWeatherMapResponse openWeatherMapResponse)
  {
    Main mainTemp = openWeatherMapResponse.getMain();
    return getWeatherDetails(openWeatherMapResponse, mainTemp);
  }

  private WeatherDetails getWeatherDetails(OpenWeatherMapResponse openWeatherMapResponse, Main mainTemp)
  {
    WeatherDetails weatherDetails = new WeatherDetails();
    weatherDetails.setTemp(mainTemp.getTemp());
    weatherDetails.setTemp_min(mainTemp.getTemp_min());
    weatherDetails.setTemp_max(mainTemp.getTemp_max());
    weatherDetails.setHumidity(mainTemp.getHumidity());
    weatherDetails.setFeels_like(mainTemp.getFeels_like());
    weatherDetails.setPressure(mainTemp.getPressure());
    weatherDetails.setActionDateTime(LocalDateTime.now());

    if (!CollectionUtils.isEmpty(openWeatherMapResponse.getWeather())) {
      weatherDetails.setDescription(openWeatherMapResponse.getWeather().get(0).getMain());
    }
    return weatherDetails;
  }
}
