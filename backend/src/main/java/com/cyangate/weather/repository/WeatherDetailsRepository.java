package com.cyangate.weather.repository;

import com.cyangate.weather.entity.WeatherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDetailsRepository extends JpaRepository<WeatherDetails, Long>
{
}