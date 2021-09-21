package com.cyangate.weather.config;

import com.cyangate.weather.entity.Location;
import com.cyangate.weather.entity.WeatherDetails;
import com.cyangate.weather.processors.WeatherProcessor;
import com.cyangate.weather.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Class is used to define the camel routes which get the weather information from openweathermap api
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class WeatherRouteConfig extends RouteBuilder
{

  private final OpenWeatherMapProperties openWeatherMapProperties;
  private final LocationRepository locationRepository;
  private final WeatherProcessor weatherProcessor;

  @Override
  public void configure()
  {
    // separate route for each city and country which is defined in database
    for (Location location: locationRepository.findAll()) {
      log.info("Preparing the route for city {} and country {}", location.getCityName(), location.getCountryName());

      from(prepareSource(location))
              .setHeader("LocationId", constant(location.getId()))
              .process(weatherProcessor)
              .to("jpa:" + WeatherDetails.class.getName() + "?usePersist=true");
    }
  }

  private String prepareSource(Location location)
  {
    String url = "weather:" + location.getCityName() + location.getId(); // to make the source unique, append id
    // Query parameters
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
            // Add query parameter
            .queryParam("location", location.getCityName() + ","  + location.getCountryName())
            .queryParam("appid", openWeatherMapProperties.getApiKey())
            .queryParam("units", openWeatherMapProperties.getUnit())
            .queryParam("delay", openWeatherMapProperties.getDelay())
            .queryParam("timeUnit", openWeatherMapProperties.getTimeUnit());
    return builder.toUriString();
  }

}
