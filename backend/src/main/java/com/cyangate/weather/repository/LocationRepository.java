package com.cyangate.weather.repository;

import com.cyangate.weather.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>
{
  Location findByCityNameAndCountryName(String city, String country);
}
