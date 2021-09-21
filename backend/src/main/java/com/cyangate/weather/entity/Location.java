package com.cyangate.weather.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity class for Location table
 */
@Table(name = "Location")
@Entity
@Getter
@Setter
public class Location
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Location_ID", nullable = false)
  private Long id;

  @Column(name = "City_Name", nullable = false, length = 100)
  private String cityName;

  @Column(name = "City_ID")
  private Integer cityId;

  @Column(name = "Country_Name", nullable = false, length = 100)
  private String countryName;

  @Column(name = "Country_Code")
  private Integer countryCode;

  @Column(name = "State_Code")
  private Integer stateCode;

  @OneToMany(mappedBy="location")
  @OrderBy("actionDateTime DESC")
  private List<WeatherDetails> weatherDetails;

}