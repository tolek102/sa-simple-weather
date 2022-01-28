package pl.springacademy.simpleweather.controller.model;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WeatherData {

    Double temp;
    Double feelsLike;
    Double tempMin;
    Double tempMax;
    Integer humidity;
    Integer pressure;

    Integer windDirection;
    Double windSpeed;

    String iconUrl;

    LocalTime sunrise;
    LocalTime sunset;

    String city;
    String country;

    String errorMessage;

}
