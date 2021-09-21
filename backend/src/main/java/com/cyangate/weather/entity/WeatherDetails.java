package com.cyangate.weather.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Entity class for WeatherDetails table
 */
@Table(name = "WeatherDetails")
@Entity
@Getter
@Setter
public class WeatherDetails
{
  @Id
  @Column(name = "Weather_Details_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Temp", nullable = false)
  public Double temp;

  @Column(name = "Feels_Like")
  public Double feels_like;

  @Column(name = "Temp_Min")
  public Double temp_min;

  @Column(name = "Temp_Max")
  public Double temp_max;

  @Column(name = "Pressure")
  public Integer pressure;

  @Column(name = "Humidity")
  public Integer humidity;

  @Column(name = "Description")
  public String description;

  @Column(name = "Action_Date_Time", nullable = false)
  private LocalDateTime actionDateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Location_Id")
  private Location location;

}
