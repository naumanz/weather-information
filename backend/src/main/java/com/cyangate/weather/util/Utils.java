package com.cyangate.weather.util;

import com.cyangate.weather.dto.OpenWeatherMapResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * weather project utility class
 */
public final class Utils
{
  /**
   * Convert the openWeatherMap json response to java model objects
   * @param jsonResponse
   * @return OpenWeatherMapResponse openWeatherMapResponse
   * @throws JsonProcessingException
   */
  public static OpenWeatherMapResponse toModel(String jsonResponse) throws JsonProcessingException
  {
    ObjectMapper om = new ObjectMapper();
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return om.readValue(jsonResponse, OpenWeatherMapResponse.class);
  }

}
