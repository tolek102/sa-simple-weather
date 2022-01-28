package pl.springacademy.simpleweather.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.springacademy.simpleweather.controller.model.WeatherData;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final OpenWeatherService openWeatherService;

    public WeatherData getWeatherByCity(final String city) {

        openWeatherService.getOpenWeatherDataByCity(city);

        return WeatherData.builder().build();

    }
}
